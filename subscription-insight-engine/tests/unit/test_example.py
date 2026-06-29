"""
Example unit tests for the churn scoring logic.

These tests mock any Kafka or external dependencies so they can run
without a broker — that's what makes them "unit" tests.
"""

from unittest.mock import AsyncMock, MagicMock, patch
import pytest


# ── Example: testing churn scoring in isolation ──────────────────────────────

class TestChurnScorer:
    """Unit tests for churn risk scoring logic."""

    def test_high_churn_score_for_inactive_user(self):
        """A user with no activity in 90 days should score > 0.7."""
        # Import your actual scorer here, e.g.:
        # from app.scoring import ChurnScorer
        # scorer = ChurnScorer()
        # score = scorer.score(days_inactive=90, plan="basic")
        # assert score > 0.7
        pass  # replace with real test once scorer is importable

    def test_low_churn_score_for_active_user(self):
        """A recently active premium user should score < 0.3."""
        pass


# ── Example: mocking the Kafka producer ──────────────────────────────────────

class TestChurnEventPublisher:
    """Unit tests for the event publisher — Kafka is fully mocked."""

    @pytest.mark.asyncio
    async def test_publish_churn_event_calls_producer(self):
        """Publisher should call send() exactly once with the right topic."""
        mock_producer = AsyncMock()

        # Patch wherever your code imports the producer from, e.g.:
        # with patch("app.publisher.AIOKafkaProducer", return_value=mock_producer):
        #     publisher = ChurnEventPublisher()
        #     await publisher.publish({"subscription_id": "123", "risk": 0.85})
        #     mock_producer.send.assert_awaited_once()
        #     call_args = mock_producer.send.call_args
        #     assert call_args[0][0] == "churn-events"   # correct topic
        pass

    @pytest.mark.asyncio
    async def test_publish_does_not_raise_on_serialisation_error(self):
        """Publisher should catch serialisation errors and not crash the app."""
        pass
