CREATE TABLE "user_profile_validator_workflow" (
  "id" bigserial PRIMARY KEY,
  "user_id" varchar NOT NULL,
  "request_id" varchar NOT NULL,
  "new_value" varchar NOT NULL,
  "operation" varchar NOT NULL,
  "validation_type" varchar NOT NULL,
  "state" varchar NOT NULL,
  "created_at" timestamp NOT NULL,
  "updated_at" timestamp NOT NULL
);

CREATE TABLE "failure_reason" (
  "id" bigserial PRIMARY KEY,
  "user_profile_validator_workflow_id" bigint NOT NULL,
  "reason" varchar NOT NULL,
  "created_at" timestamp NOT NULL
);

CREATE INDEX "user_profile_validator_workflow_user_id_request_id_idx" ON "user_profile_validator_workflow" ("user_id", "request_id");

CREATE UNIQUE INDEX "failure_reason_user_profile_validator_workflow_id_idx" ON "failure_reason" ("user_profile_validator_workflow_id");

ALTER TABLE "failure_reason" ADD FOREIGN KEY ("user_profile_validator_workflow_id") REFERENCES "user_profile_validator_workflow" ("id");
