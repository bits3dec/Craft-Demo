CREATE TYPE "product" AS ENUM (
  'QUICKBOOKS_ACCOUNTING',
  'QUICKBOOKS_PAYROLL',
  'QUICKBOOKS_PAYMENTS',
  'TSHEETS'
);

CREATE TYPE "product_subscription_status" AS ENUM (
  'ACTIVE',
  'CLOSED'
);

CREATE TABLE "product_subscription" (
  "id" bigint PRIMARY KEY,
  "user_id" varchar NOT NULL,
  "product" product NOT NULL,
  "status" product_subscription_status NOT NULL,
  "created_at" timestamp,
  "updated_at" timestamp
);

CREATE UNIQUE INDEX "product_subscription_user_id_idx" ON "product_subscription" ("user_id");
