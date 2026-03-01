from pydantic_settings import BaseSettings

class Settings(BaseSettings):

    # Kafka
    kafka_bootstrap_servers: str = "kafka:9092"
    kafka_usage_topic: str = "usage-topic"
    kafka_group_id: str = "subscription-insight-engine"

    class Config:
        env_file = ".env"

settings = Settings()