# ===============================
# Base Image
# ===============================
FROM python:3.11-slim

# ===============================
# Environment Variables
# ===============================
ENV PYTHONDONTWRITEBYTECODE=1
ENV PYTHONUNBUFFERED=1

# ===============================
# Working Directory
# ===============================
WORKDIR /app

# ===============================
# Install System Dependencies (minimal)
# ===============================
RUN apt-get update && apt-get install -y \
    build-essential \
    && rm -rf /var/lib/apt/lists/*

# ===============================
# Install Python Dependencies
# ===============================
COPY requirements.txt .
RUN pip install --upgrade pip \
    && pip install --no-cache-dir -r requirements.txt

# ===============================
# Copy Application Code
# ===============================
COPY app ./app

# ===============================
# Expose Port
# ===============================
EXPOSE 8000

# ===============================
# Run FastAPI App
# ===============================
CMD ["uvicorn", "app.main:app", "--host", "0.0.0.0", "--port", "8000"]