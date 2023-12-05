CREATE TABLE "user_profile_workflow" (
  "id" bigserial PRIMARY KEY,
  "user_id" varchar NOT NULL,
  "request_id" varchar NOT NULL,
  "operation" varchar NOT NULL,
  "new_value" varchar NOT NULL,
  "state" varchar NOT NULL,
  "created_at" timestamp NOT NULL,
  "updated_at" timestamp NOT NULL
);

CREATE TABLE "user_profile_workflow_failure_reason" (
  "id" bigserial PRIMARY KEY,
  "user_profile_workflow_id" bigint NOT NULL,
  "reason" varchar NOT NULL,
  "created_at" timestamp NOT NULL
);

CREATE TABLE "user_profile_workflow_history" (
  "id" bigint PRIMARY KEY,
  "user_profile_workflow_id" bigint NOT NULL,
  "value" varchar NOT NULL,
  "created_at" timestamp NOT NULL
);

CREATE UNIQUE INDEX "user_profile_workflow_user_id_request_id_idx" ON "user_profile_workflow" ("user_id", "request_id");

CREATE UNIQUE INDEX "user_profile_workflow_request_id_idx" ON "user_profile_workflow" ("request_id");

CREATE INDEX "user_profile_workflow_failure_reason_user_profile_workflow_id_idx" ON "user_profile_workflow_failure_reason" ("user_profile_workflow_id");

CREATE INDEX "user_profile_workflow_history_user_profile_workflow_id_idx" ON "user_profile_workflow_history" ("user_profile_workflow_id");

ALTER TABLE "user_profile_workflow_failure_reason" ADD FOREIGN KEY ("user_profile_workflow_id") REFERENCES "user_profile_workflow" ("id");

ALTER TABLE "user_profile_workflow_history" ADD FOREIGN KEY ("user_profile_workflow_id") REFERENCES "user_profile_workflow" ("id");
