CREATE TYPE "operation" AS ENUM (
  'CREATE',
  'UPDATE'
);

CREATE TYPE "state" AS ENUM (
  'IN_PROGRESS',
  'ACCEPTED',
  'FAILURE'
);

CREATE TYPE "validation_type" AS ENUM (
  'BASIC',
  'QUICKBOOKS_ACCOUNTING',
  'QUICKBOOKS_PAYROLL',
  'QUICKBOOKS_PAYMENTS',
  'TSHEETS'
);

CREATE TABLE "user_profile_validator_workflow" (
  "id" bigint PRIMARY KEY,
  "user_id" varchar NOT NULL,
  "request_id" varchar NOT NULL,
  "new_value" varchar NOT NULL,
  "operation" operation NOT NULL,
  "validation_type" validation_type NOT NULL,
  "state" state NOT NULL,
  "created_at" timestamp NOT NULL,
  "updated_at" timestamp NOT NULL
);

CREATE TABLE "failure_reason" (
  "id" bigint PRIMARY KEY,
  "user_profile_validator_workflow_id" bigint NOT NULL,
  "reason" varchar NOT NULL,
  "created_at" timestamp NOT NULL
);

CREATE UNIQUE INDEX "user_profile_validator_workflow_user_id_request_id_idx" ON "user_profile_validator_workflow" ("user_id", "request_id");

CREATE INDEX "user_profile_validator_workflow_request_id_idx" ON "user_profile_validator_workflow" ("request_id");

CREATE UNIQUE INDEX "failure_reason_user_profile_validator_workflow_id_idx" ON "failure_reason" ("user_profile_validator_workflow_id");

ALTER TABLE "failure_reason" ADD FOREIGN KEY ("user_profile_validator_workflow_id") REFERENCES "user_profile_validator_workflow" ("id");
