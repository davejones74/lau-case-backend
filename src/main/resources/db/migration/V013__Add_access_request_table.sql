CREATE TABLE access_request_audit (
  id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  request_type VARCHAR(64) NOT NULL,
  user_id VARCHAR(64) NOT NULL,
  case_ref VARCHAR(16) NOT NULL,
  reason VARCHAR NOT NULL,
  action VARCHAR(64) NOT NULL,
  time_limit TIMESTAMP WITH TIME ZONE,
  log_timestamp TIMESTAMP WITH TIME ZONE NOT NULL
);

-- access_request_audit comments
comment on column access_request_audit.id is 'synthetic primary key';
comment on column access_request_audit.request_type is 'One of - challenged or specific';
comment on column access_request_audit.user_id is 'user id either requesting access or approving';
comment on column access_request_audit.case_ref is 'case reference number';
comment on column access_request_audit.reason is 'reason for access request';
comment on column access_request_audit.action is 'action taken on request, one of "created", "approved", "rejected"';
comment on column access_request_audit.time_limit is 'time limit for "specific" access request';
comment on column access_request_audit.log_timestamp is 'timestamp of request';

-- access_request_audit indexes
CREATE INDEX access_request_audit_user_id_idx ON access_request_audit (user_id);
CREATE INDEX access_request_audit_case_id_idx ON access_request_audit (case_ref);
CREATE INDEX access_request_audit_request_type_idx ON access_request_audit (request_type);
CREATE INDEX access_request_audit_log_timestamp_idx ON access_request_audit (log_timestamp ASC);
