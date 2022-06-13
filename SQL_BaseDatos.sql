drop table PLAM_EMPLEADOS cascade constraints;
drop table PLAM_PLANILLAS cascade constraints;
drop table PLAM_EMPLADOSPLANILLA cascade constraints;
create table PLAM_EMPLEADOS
(
  emp_id            NUMBER not null,
  emp_nombre        VARCHAR2(30) not null,
  emp_papellido     VARCHAR2(15) not null,
  emp_sapellido     VARCHAR2(15) not null,
  emp_cedula        VARCHAR2(40) not null,
  emp_genero        VARCHAR2(1) not null,
  emp_correo        VARCHAR2(80),
  emp_administrador VARCHAR2(1) not null,
  emp_usuario       VARCHAR2(15),
  emp_clave         VARCHAR2(8),
  emp_fingreso      DATE not null,
  emp_fsalida       DATE,
  emp_estado        VARCHAR2(1) not null,
  emp_version       NUMBER default 1 not null
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table PLAM_EMPLEADOS
  is 'Tabla para almacenar registro de empleados.';
comment on column PLAM_EMPLEADOS.emp_id
  is 'Id del registro(empleado)';
comment on column PLAM_EMPLEADOS.emp_nombre
  is 'Nombre del emplado';
comment on column PLAM_EMPLEADOS.emp_papellido
  is 'Primer apellido del empleado';
comment on column PLAM_EMPLEADOS.emp_sapellido
  is 'Segundo apellido del emplado.';
comment on column PLAM_EMPLEADOS.emp_cedula
  is 'Cedula del emplado.';
comment on column PLAM_EMPLEADOS.emp_genero
  is 'Genero del emplado(M:Masculino, F:Femenino ).';
comment on column PLAM_EMPLEADOS.emp_correo
  is 'Correo del emplado.';
comment on column PLAM_EMPLEADOS.emp_administrador
  is 'Estado del administrador(S:si, N:no)';
comment on column PLAM_EMPLEADOS.emp_usuario
  is 'Usuario del emplado.
';
comment on column PLAM_EMPLEADOS.emp_clave
  is 'Clave del empleado.';
comment on column PLAM_EMPLEADOS.emp_fingreso
  is 'Fecha de ingreso del empleado';
comment on column PLAM_EMPLEADOS.emp_fsalida
  is 'Fecha de salida del empleado';
comment on column PLAM_EMPLEADOS.emp_estado
  is 'Estado del empleado(A:Activo, I:Inactivo).';
comment on column PLAM_EMPLEADOS.emp_version
  is 'Version del registro de empleados,';
create unique index PLAM_EMPLEADOS_UNQ01 on PLAM_EMPLEADOS (EMP_USUARIO)
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table PLAM_EMPLEADOS
  add constraint PLAM_EMPLEADOS_PK primary key (EMP_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table PLAM_EMPLEADOS
  add constraint PLAM_EMPLEADOS_CK01
  check (emp_genero in ('F','M'));
alter table PLAM_EMPLEADOS
  add constraint PLAM_EMPLEADOS_CK02
  check (emp_administrador in ('S','N'));
alter table PLAM_EMPLEADOS
  add constraint PLAM_EMPLEADOS_CK03
  check (emp_estado in ('A','I'));

create table PLAM_PLANILLAS
(
  tpla_id          NUMBER not null,
  tpla_codigo      VARCHAR2(4) not null,
  tpla_descripcion VARCHAR2(40) not null,
  tpla_plaxmes     NUMBER not null,
  tpla_anoultpla   NUMBER,
  tpla_mesultpla   NUMBER,
  tpla_numultpla   NUMBER,
  tpla_estado      VARCHAR2(1) not null,
  tpla_version     NUMBER default 1 not null
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table PLAM_PLANILLAS
  is 'Tabla para almacenar registrar informacion de tipo planillas.';
comment on column PLAM_PLANILLAS.tpla_id
  is 'ID de tipo planillas';
comment on column PLAM_PLANILLAS.tpla_codigo
  is 'Codigo de tipo planilla.';
comment on column PLAM_PLANILLAS.tpla_descripcion
  is 'Descripcion de tipo planilla.';
comment on column PLAM_PLANILLAS.tpla_plaxmes
  is 'Planilla por mes.';
comment on column PLAM_PLANILLAS.tpla_anoultpla
  is 'Año de la ultima planilla.';
comment on column PLAM_PLANILLAS.tpla_mesultpla
  is 'Mes de la ultima planilla.';
comment on column PLAM_PLANILLAS.tpla_numultpla
  is 'Numero de ultima planilla.';
comment on column PLAM_PLANILLAS.tpla_estado
  is 'Estado de ultima planilla(A:Activo, I:Inactivo)';
comment on column PLAM_PLANILLAS.tpla_version
  is 'Version del registro tipo planilla,';
create unique index PLAM_PLANILLAS_UNQ01 on PLAM_PLANILLAS (TPLA_CODIGO)
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table PLAM_PLANILLAS
  add constraint PLAM_PLANILLAS_PK primary key (TPLA_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table PLAM_PLANILLAS
  add constraint PLAM_PLANILLAS_CK01
  check (tpla_estado in ('A','I'));

create table PLAM_EMPLADOSPLANILLA
(
  exp_idtpla NUMBER not null,
  exp_idemp  NUMBER not null
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column PLAM_EMPLADOSPLANILLA.exp_idtpla
  is 'Id de tipo de planilla.';
comment on column PLAM_EMPLADOSPLANILLA.exp_idemp
  is 'Id de empleados.';
alter table PLAM_EMPLADOSPLANILLA
  add constraint PLAM_EMPLADOSPLANILLA_PK primary key (EXP_IDTPLA, EXP_IDEMP)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table PLAM_EMPLADOSPLANILLA
  add constraint PLAM_EMPLADOSPLANILLA_FK01 foreign key (EXP_IDTPLA)
  references PLAM_PLANILLAS (TPLA_ID);
alter table PLAM_EMPLADOSPLANILLA
  add constraint PLAM_EMPLADOSPLANILLA_FK02 foreign key (EXP_IDEMP)
  references PLAM_EMPLEADOS (EMP_ID);

alter table PLAM_EMPLADOSPLANILLA disable constraint PLAM_EMPLADOSPLANILLA_FK01;
alter table PLAM_EMPLADOSPLANILLA disable constraint PLAM_EMPLADOSPLANILLA_FK02;
insert into PLAM_EMPLEADOS (emp_id, emp_nombre, emp_papellido, emp_sapellido, emp_cedula, emp_genero, emp_correo, emp_administrador, emp_usuario, emp_clave, emp_fingreso, emp_fsalida, emp_estado, emp_version)
values (2, 'Harold', 'Avila', 'Hernandez', '118470855', 'M', 'Harold@gmail.com', 'N', null, null, to_date('05-01-2022', 'dd-mm-yyyy'), null, 'A', 1);
insert into PLAM_EMPLEADOS (emp_id, emp_nombre, emp_papellido, emp_sapellido, emp_cedula, emp_genero, emp_correo, emp_administrador, emp_usuario, emp_clave, emp_fingreso, emp_fsalida, emp_estado, emp_version)
values (1, 'Anthony', 'Avila', 'Hernandez', '118470854', 'M', 'Anthonyah131@gmail.com', 'S', 'Anthonyah', '12345', to_date('10-05-2022', 'dd-mm-yyyy'), null, 'A', 1);
commit;
insert into PLAM_PLANILLAS (tpla_id, tpla_codigo, tpla_descripcion, tpla_plaxmes, tpla_anoultpla, tpla_mesultpla, tpla_numultpla, tpla_estado, tpla_version)
values (3, 'eee', 'dbbcskd', 5, null, null, null, 'A', 1);
insert into PLAM_PLANILLAS (tpla_id, tpla_codigo, tpla_descripcion, tpla_plaxmes, tpla_anoultpla, tpla_mesultpla, tpla_numultpla, tpla_estado, tpla_version)
values (5, 'fmp', 'dvsdv', 2, null, null, null, 'A', 3);
insert into PLAM_PLANILLAS (tpla_id, tpla_codigo, tpla_descripcion, tpla_plaxmes, tpla_anoultpla, tpla_mesultpla, tpla_numultpla, tpla_estado, tpla_version)
values (1, 'emp', 'Tabla para pagos nueva', 2, null, null, null, 'A', 9);
commit;
insert into PLAM_EMPLADOSPLANILLA (exp_idtpla, exp_idemp)
values (1, 1);
insert into PLAM_EMPLADOSPLANILLA (exp_idtpla, exp_idemp)
values (1, 2);
insert into PLAM_EMPLADOSPLANILLA (exp_idtpla, exp_idemp)
values (3, 1);
insert into PLAM_EMPLADOSPLANILLA (exp_idtpla, exp_idemp)
values (5, 1);
insert into PLAM_EMPLADOSPLANILLA (exp_idtpla, exp_idemp)
values (5, 2);
commit;
alter table PLAM_EMPLADOSPLANILLA enable constraint PLAM_EMPLADOSPLANILLA_FK01;
alter table PLAM_EMPLADOSPLANILLA enable constraint PLAM_EMPLADOSPLANILLA_FK02;
