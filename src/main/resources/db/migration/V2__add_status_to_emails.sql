alter table emails
    add column status varchar(20)not null default 'PENDING',
    add column attempt_count INT not null default 0,
    add column last_error varchar (1000),
    add column sent_at TIMESTAMP;






