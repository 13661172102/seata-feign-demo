create table service_at
(
    id   serial,
    name varchar(25) null,
    CONSTRAINT pk_service_at PRIMARY KEY (id)
);

create table service_tcc
(
    id    serial,
    name varchar(25) null,
    CONSTRAINT pk_service_tcc PRIMARY KEY (id)

);

create table service_tm
(
    id    serial,
    aName varchar(25) not null,
    CONSTRAINT pk_service_tm PRIMARY KEY (id)

);


-- for AT mode you must to init this sql for you business database. the seata server not need it.
CREATE TABLE IF NOT EXISTS public.undo_log
(
    id            SERIAL       NOT NULL,
    branch_id     BIGINT       NOT NULL,
    xid           VARCHAR(128) NOT NULL,
    context       VARCHAR(128) NOT NULL,
    rollback_info BYTEA        NOT NULL,
    log_status    INT          NOT NULL,
    log_created   TIMESTAMP(0) NOT NULL,
    log_modified  TIMESTAMP(0) NOT NULL,
    CONSTRAINT pk_undo_log PRIMARY KEY (id),
    CONSTRAINT ux_undo_log UNIQUE (xid, branch_id)
);
CREATE INDEX ix_log_created ON undo_log(log_created);

COMMENT ON TABLE public.undo_log IS 'AT transaction mode undo table';
COMMENT ON COLUMN public.undo_log.branch_id IS 'branch transaction id';
COMMENT ON COLUMN public.undo_log.xid IS 'global transaction id';
COMMENT ON COLUMN public.undo_log.context IS 'undo_log context,such as serialization';
COMMENT ON COLUMN public.undo_log.rollback_info IS 'rollback info';
COMMENT ON COLUMN public.undo_log.log_status IS '0:normal status,1:defense status';
COMMENT ON COLUMN public.undo_log.log_created IS 'create datetime';
COMMENT ON COLUMN public.undo_log.log_modified IS 'modify datetime';

CREATE SEQUENCE IF NOT EXISTS undo_log_id_seq INCREMENT BY 1 MINVALUE 1 ;

CREATE TABLE IF NOT EXISTS public.tcc_fence_log
(
    xid              VARCHAR(128)  NOT NULL,
    branch_id        BIGINT        NOT NULL,
    action_name      VARCHAR(64)   NOT NULL,
    status           SMALLINT      NOT NULL,
    gmt_create       TIMESTAMP(3)  NOT NULL,
    gmt_modified     TIMESTAMP(3)  NOT NULL,
    CONSTRAINT pk_tcc_fence_log PRIMARY KEY (xid, branch_id)
);
CREATE INDEX idx_gmt_modified ON public.tcc_fence_log (gmt_modified);
CREATE INDEX idx_status ON public.tcc_fence_log (status);