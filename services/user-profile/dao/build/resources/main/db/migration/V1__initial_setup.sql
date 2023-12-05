--CREATE TYPE "tax_type" AS ENUM (
--  'PAN',
--  'EIN'
--);
--
--CREATE TYPE "operation" AS ENUM (
--  'CREATE',
--  'UPDATE'
--);
--
--CREATE TYPE "state" AS ENUM (
--  'IN_PROGRESS',
--  'ACCEPTED',
--  'REJECTED'
--);

CREATE TABLE "user_profile" (
  "id" bigserial PRIMARY KEY,
  "user_id" varchar NOT NULL,
  "company_name" varchar NOT NULL,
  "legal_name" varchar NOT NULL,
  "business_address_id" bigint NOT NULL,
  "legal_address_id" bigint NOT NULL,
  "tax_identifier_id" bigint NOT NULL,
  "email" varchar NOT NULL,
  "website" varchar NOT NULL,
  "created_at" timestamp NOT NULL,
  "updated_at" timestamp NOT NULL
);

CREATE TABLE "address" (
  "id" bigserial PRIMARY KEY,
  "line1" varchar NOT NULL,
  "line2" varchar NOT NULL,
  "city" varchar NOT NULL,
  "state" varchar NOT NULL,
  "country" varchar NOT NULL,
  "zip" varchar NOT NULL,
  "created_at" timestamp NOT NULL,
  "updated_at" timestamp NOT NULL
);

CREATE TABLE "tax_identifier" (
  "id" bigserial PRIMARY KEY,
  "value" varchar NOT NULL,
  "type" varchar NOT NULL,
  "created_at" timestamp NOT NULL,
  "updated_at" timestamp NOT NULL
);

CREATE TABLE "user_profile_request" (
  "id" bigserial PRIMARY KEY,
  "request_id" varchar NOT NULL,
  "user_id" varchar NOT NULL,
  "operation" varchar NOT NULL,
  "state" varchar NOT NULL,
  "new_value" varchar NOT NULL,
  "created_at" timestamp NOT NULL,
  "updated_at" timestamp NOT NULL
);

CREATE TABLE "user_profile_history" (
  "id" bigserial PRIMARY KEY,
  "user_id" varchar NOT NULL,
  "user_profile_version" bigint NOT NULL,
  "value" varchar NOT NULL,
  "created_at" timestamp NOT NULL
);

CREATE UNIQUE INDEX "user_profile_user_id_idx" ON "user_profile" ("user_id");

CREATE UNIQUE INDEX "user_profile_business_address_id_id_idx" ON "user_profile" ("business_address_id");

CREATE UNIQUE INDEX "user_profile_legal_address_id_idx" ON "user_profile" ("legal_address_id");

CREATE UNIQUE INDEX "user_profile_tax_identifier_id_idx" ON "user_profile" ("tax_identifier_id");

CREATE UNIQUE INDEX "user_profile_request_user_id_request_id_idx" ON "user_profile_request" ("user_id", "request_id");

CREATE UNIQUE INDEX "user_profile_history_user_id_idx" ON "user_profile_history" ("user_id");

ALTER TABLE "user_profile" ADD FOREIGN KEY ("business_address_id") REFERENCES "address" ("id");

ALTER TABLE "user_profile" ADD FOREIGN KEY ("legal_address_id") REFERENCES "address" ("id");

ALTER TABLE "user_profile" ADD FOREIGN KEY ("tax_identifier_id") REFERENCES "tax_identifier" ("id");

ALTER TABLE "user_profile_history" ADD FOREIGN KEY ("user_id") REFERENCES "user_profile" ("user_id");
