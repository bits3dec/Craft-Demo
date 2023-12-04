CREATE TYPE "operation" AS ENUM (
  'CREATE',
  'UPDATE'
);

CREATE TYPE "state" AS ENUM (
  'INITIATED',
  'PROFILE_VALIDATION_INITIATED',
  'PROFILE_VALIDATION_RECEIVED',
  'ACCEPTED',
  'FAILURE'
);

CREATE TABLE "user_profile_workflow" (
  "id" bigint PRIMARY KEY,
  "user_id" varchar NOT NULL,
  "request_id" varchar NOT NULL,
  "operation" operation NOT NULL,
  "new_value" varchar NOT NULL,
  "state" state NOT NULL,
  "created_at" timestamp NOT NULL,
  "updated_at" timestamp NOT NULL
);

CREATE TABLE "user_profile_workflow_failure_reason" (
  "id" bigint PRIMARY KEY,
  "request_id" varchar NOT NULL,
  "reason" varchar NOT NULL,
  "created_at" timestamp NOT NULL
);

CREATE TABLE "user_profile_workflow_history" (
  "id" bigint PRIMARY KEY,
  "request_id" varchar NOT NULL,
  "value" varchar NOT NULL,
  "created_at" timestamp NOT NULL
);

CREATE UNIQUE INDEX "user_profile_workflow_user_id_request_id_idx" ON "user_profile_workflow" ("user_id", "request_id");

CREATE UNIQUE INDEX "user_profile_workflow_request_id_idx" ON "user_profile_workflow" ("request_id");

CREATE UNIQUE INDEX "user_profile_workflow_failure_reason_idx" ON "user_profile_workflow_failure_reason" ("request_id");

CREATE UNIQUE INDEX "user_profile_workflow_history_request_id_idx" ON "user_profile_workflow_history" ("request_id");

ALTER TABLE "user_profile_workflow_failure_reason" ADD FOREIGN KEY ("request_id") REFERENCES "user_profile_workflow" ("request_id");
