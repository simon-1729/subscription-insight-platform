import asyncio
from fastapi import FastAPI, HTTPException
from fastapi.concurrency import asynccontextmanager

from app.llm.client_test import openai_client_test
from app.messaging.kafka.consumer import KafkaConsumerService
from app.messaging.kafka.producer import KafkaProducerService
from app.config.settings import settings


consumer = KafkaConsumerService(
    topic=settings.kafka_usage_topic,
    bootstrap_servers=settings.kafka_bootstrap_servers,
    group_id=settings.kafka_group_id
)
producer = KafkaProducerService(
    bootstrap_servers=settings.kafka_bootstrap_servers
)

@asynccontextmanager
async def lifespan(app: FastAPI):
    await consumer.start()
    await producer.start()


    async def handler(event):
        print("Received event:", event)

        # For testing purposes
        assessed = {
            "original": event,
            "risk_score": 0.87,
            "risk_level": "high"
        }

        await producer.send("risk-assessed-topic", assessed)
        print("Insight Engine: Pushed to risk-assessed-topic", assessed)

    # create_task() registers listen() with the event loop
    task = asyncio.create_task(consumer.listen(handler))

    try:
        yield
    finally:
        task.cancel()
        await consumer.stop()
        await producer.stop()

app = FastAPI(lifespan=lifespan)


@app.get("/")
def read_root():
    return {"message": "AI Subscription Insight Engine Running..."}

@app.get("/llm-test")
async def llm_test():
    try:
        result = await openai_client_test()
        return {"response": result}
    except Exception as e:
        print("LLM test failed", e)
        raise HTTPException(
            status_code=500,
            detail="LLM test failed. Check logs for details."
        )
