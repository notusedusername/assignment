ALTER TABLE palindrome
ALTER COLUMN "timestamp" TYPE timestamp with time zone;

ALTER TABLE palindrome
ADD CONSTRAINT palindrome_pk PRIMARY KEY (id)