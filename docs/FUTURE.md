# Future Direction — Adaptive Model Bootstrapping

## The Problem

A new business adopting Subscription Insight Platform faces a cold start problem — without 
sufficient historical subscription data, there is no labelled dataset from which to train 
a meaningful churn model. The platform's Analytics Engine requires a trained XGBoost model 
to function, but that model needs data to learn from.

This is a common and practical barrier to ML adoption for early-stage or smaller businesses.

---

## Proposed Solution — Schema-Aware Dataset Matching

A tool that allows a business to define or upload their subscription schema and automatically 
maps it to a curated library of publicly available churn datasets. The closest matching 
dataset is used to train the XGBoost model, giving the business a working churn model 
from day one — before they have accumulated enough of their own data.

As the business grows and accumulates labelled churn data of their own, the model can be 
progressively retrained on their own data, reducing reliance on the proxy dataset over time.

---

## How It Would Work

```
Business defines their subscription schema
        ↓
Tool analyses field names, types, and descriptions
        ↓
LLM performs semantic matching against dataset library
        ↓
Confidence score assigned per field mapping
        ↓
User reviews and overrides low-confidence mappings
        ↓
Model trained on best-matched dataset using confirmed mapping
        ↓
Model deployed to Analytics Engine, ready for inference
```

---

## Dataset Library

An initial library of publicly available churn datasets covering common subscription domains:

| Dataset | Domain | Key Churn Signals |
|---|---|---|
| IBM Telco Customer Churn | Telecoms | Tenure, contract type, monthly charges, support tickets |
| Kaggle E-commerce Churn | Retail subscriptions | Order recency, frequency, complaints, satisfaction score |
| Bank Customer Churn | Financial services | Account tenure, product count, transaction activity |
| SaaS Churn Dataset | Software subscriptions | Login frequency, feature adoption, seat utilisation |

The library is designed to be extensible — new datasets can be added as community 
contributions or business-specific additions.

---

## Semantic Field Matching

Simple string matching is insufficient for this problem. Two fields named `customer_since_date` 
and `tenure_months` are semantically equivalent but look completely different. An LLM is 
used to reason about semantic equivalence across schema fields:

```
User field:       "subscription_start_date"  (date the customer first subscribed)
Dataset field:    "tenure"                   (number of months as a customer)
Match confidence: high — derivable from subscription_start_date with a date calculation
Transformation:   tenure = (current_date - subscription_start_date).months
```

Where a direct mapping exists the field is used as-is. Where a transformation is required 
it is generated and applied automatically. Where no reasonable match exists the gap is 
flagged explicitly so the user can decide whether to enrich their schema or accept reduced 
model coverage.

---

## Confidence Scoring

Not all field mappings are equally reliable. The tool assigns a confidence score to each 
mapping and surfaces low-confidence matches for human review before training proceeds:

| Confidence | Meaning | Action |
|---|---|---|
| High | Strong semantic and structural match | Auto-accepted |
| Medium | Plausible match with minor differences | Flagged for review |
| Low | Weak or inferred match | Requires explicit user confirmation |
| None | No match found | User prompted to add field or accept gap |

This keeps the human in the loop for decisions that materially affect model quality — 
consistent with the design philosophy of the broader platform.

---

## Training / Serving Skew Acknowledgement

A model trained on a proxy dataset will not perfectly represent the business's own customer 
behaviour. Feature distributions, cultural context, pricing norms, and product semantics 
will differ. The tool is explicit about this limitation:

- A skew report is generated at training time, highlighting which mapped fields have the 
  greatest distributional differences
- Model confidence scores at inference time reflect the degree of skew in the features 
  driving each prediction
- The tool tracks what percentage of inference events match the training distribution, 
  providing an ongoing signal for when retraining on proprietary data becomes worthwhile

---

## Relationship to Subscription Insight Platform

This is a natural Phase 3 extension of the platform, building directly on the Analytics 
Engine introduced in Phase 2. The Subscription Insight Platform already demonstrates the 
end-to-end flow from event ingestion to churn scoring — this tool addresses the prerequisite 
question of how a new business gets a working model in the first place.

Phase 2 provides the concrete working example this tool is built around: the IBM Telco 
dataset mapped to a synthetic subscription schema, demonstrating that a proxy dataset can 
produce meaningful churn predictions on a different domain's events.
