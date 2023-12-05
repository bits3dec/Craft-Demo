CREATE TABLE "product_subscription" (
  "id" bigserial PRIMARY KEY,
  "user_id" varchar NOT NULL,
  "product" varchar NOT NULL,
  "status" varchar NOT NULL,
  "created_at" timestamp,
  "updated_at" timestamp
);

CREATE UNIQUE INDEX "product_subscription_user_id_product_idx" ON "product_subscription" ("user_id", "product");
