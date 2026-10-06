-- 1. individualClientReferenceId was left out of the persister identifier inserts after V20250508122000,
--    so it is null on rows written since then; the field app cannot decode identifiers without it
UPDATE INDIVIDUAL_IDENTIFIER ii
    SET individualClientReferenceId = ind.clientReferenceId
    FROM INDIVIDUAL ind
    WHERE ii.individualId = ind.id
      AND ii.individualClientReferenceId IS NULL
      AND ind.clientReferenceId IS NOT NULL;

-- 2. The persister binds clientReferenceId explicitly, so a missing value was stored as null
--    instead of falling back to the column default
UPDATE INDIVIDUAL_IDENTIFIER
    SET clientReferenceId = gen_random_uuid()::text
    WHERE clientReferenceId IS NULL;
