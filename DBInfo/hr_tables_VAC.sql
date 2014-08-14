CREATE TABLE AC0001(
	[AC0001_SYS_NUM] [varchar](30) NOT NULL,
	[AC0001_SYS_NAME] [varchar](150) NULL,
	[AC0001_STATUS] [varchar](5) NULL,
	[AC0001_PARENT_NUM] [varchar](30) NULL,
	[AC0001_ORDER_NUM] [int] NULL,
	[AC0001_DES] [varchar](2000) NULL,
	[AC0001_COMMENT] [varchar](2000) NULL,
	[AC0001_CREATE_USER] [varchar](10) NULL,
	[AC0001_CREATE_DATE] [datetime] NULL,
	[AC0001_EDIT_USER] [varchar](10) NULL,
	[AC0001_EDIT_DATE] [datetime] NULL,
	primary key (AC0001_SYS_NUM)
);


CREATE TABLE AC0002(
	[AC0002_FUN_NUM] [varchar](30) NOT NULL,
	[AC0002_FUN_NAME] [varchar](150) NULL,
	[AC0002_SYS_NUM] [varchar](30) NULL,
	[AC0002_STATUS] [varchar](5) NULL,
	[AC0002_PARENT_NUM] [varchar](30) NULL,
	[AC0002_ORDER_NUM] [int] NULL,
	[AC0002_DES] [varchar](2000) NULL,
	[AC0002_COMMENT] [varchar](2000) NULL,
	[AC0002_CREATE_USER] [varchar](10) NULL,
	[AC0002_CREATE_DATE] [datetime] NULL,
	[AC0002_EDIT_USER] [varchar](10) NULL,
	[AC0002_EDIT_DATE] [datetime] NULL,
	primary key (AC0002_FUN_NUM)
);


CREATE TABLE [dbo].[AC0003](
	[AC0003_OPT_NUM] [varchar](30) NOT NULL,
	[AC0003_OPT_NAME] [varchar](150) NULL,
	[AC0003_STATUS] [varchar](5) NULL,
	[AC0003_DES] [varchar](2000) NULL,
	[AC0003_COMMENT] [varchar](2000) NULL,
	[AC0003_CREATE_USER] [varchar](10) NULL,
	[AC0003_CREATE_DATE] [datetime] NULL,
	[AC0003_EDIT_USER] [varchar](10) NULL,
	[AC0003_EDIT_DATE] [datetime] NULL,
	primary key (AC0003_OPT_NUM)
);


CREATE TABLE [dbo].[AC0004](
	[AC0004_NUM] [int] IDENTITY(1,1) NOT NULL,
	[AC0004_FUN_NUM] [varchar](30) NULL,
	[AC0004_OPT_NUM] [varchar](30) NULL
	primary key (AC0004_NUM)
);
/* can't use this table because it will affect TIM'S another program, can consider use it later */
/*
CREATE TABLE [dbo].[AC0004](
	[AC0004_NUM] [int] IDENTITY(1,1) NOT NULL,
	[AC0004_FUN_NUM] [varchar](30) NULL,
	[AC0004_OPT_NUM] [varchar](30) NULL,
	[AC0004_DESC] [varchar](500), 
	primary key (AC0004_NUM)
);
*/
CREATE TABLE [dbo].[AC0005](
	[AC0005_ROLE_NUM] [varchar](30) NOT NULL,
	[AC0005_ROLE_NAME] [varchar](100) NULL,
	[AC0005_ROLE_STATUS] [varchar](5) NULL,
	[AC0005_ROLE_DESC] [varchar](2000) NULL,
	[AC0005_ROLE_COMMENT] [varchar](2000) NULL,
	[AC0005_CREATE_USER] [varchar](10) NULL,
	[AC0005_CREATE_DATETIME] [datetime] NULL,
	[AC0005_EDIT_USER] [varchar](10) NULL,
	[AC0005_EDIT_DATETIME] [datetime] NULL,
	primary key (AC0005_ROLE_NUM)
);

CREATE TABLE [dbo].[AC0006](
	[AC0006_ROLE_NUM] [varchar](30) NOT NULL,
	[AC0006_SYS_NUM] [varchar](30) NOT NULL
);

CREATE TABLE [dbo].[AC0007](
	[AC0007_ROLE_NUM] [varchar](30) NOT NULL,
	[AC0007_FUN_NUM] [varchar](30) NOT NULL,
	[AC0007_SYS_NUM] [varchar](30) NOT NULL
);


CREATE TABLE [dbo].[AC0008](
	[AC0008_ROLE_NUM] [varchar](30) NOT NULL,
	[AC0008_SYS_NUM] [varchar](30) NULL,
	[AC0008_FUN_NUM] [varchar](30) NOT NULL,
	[AC0008_OPT_NUM] [varchar](30) NOT NULL
);

CREATE TABLE [dbo].[AC0009](
	[AC0009_ROLE_NUM] [varchar](30) NULL,
	[AC0009_EMPLOYEE_NUM] [varchar](10) NULL
);

CREATE TABLE [dbo].[HOLIDAY](
	[HOLIDAY_SEQ] [int] IDENTITY(1,1) NOT NULL,
	[HOLIDAY_NAME] [varchar](100) NULL,
	[HOLIDAY_DATE] [varchar](10) NULL,
	[HOLIDAY_YEAR] [varchar](4) NULL
);

CREATE TABLE [dbo].[MP0002](
	[MP0002_SEQ] [int] IDENTITY(1,1) NOT FOR REPLICATION NOT NULL,
	[MP0002_DEPARTMENT_NUM] [varchar](5) NULL,
	[MP0002_DEPARTMENT_NAME] [varchar](30) NULL,
	[MP0002_DEPARTMENT_DESC] [varchar](200) NULL,
	[MP0002_DEPARTMENT_STATUS] [varchar](1) NULL,
	[MP0002_CREATE_USER] [varchar](10) NULL,
	[MP0002_CREATE_DATETIME] [datetime] NULL,
	[MP0002_EDIT_USER] [varchar](10) NULL,
	[MP0002_EDIT_DATETIME] [datetime] NULL,
	primary key (MP0002_SEQ)
);

CREATE TABLE [dbo].[MP0005](
	[MP0005_SEQ] [int] IDENTITY(1,1) NOT NULL,
	[MP0005_DEPART_ID] [int] NULL,
	[MP0005_TITLE] [varchar](50) NULL,
	[MP0005_CONTENT] [varchar](4000) NULL,
	[MP0005_AUTHOR] [varchar](50) NULL,
	[MP0005_ADDTIME] [datetime] NULL,
	[MP0005_TYPE] [varchar](1) NULL,
	primary key (MP0005_SEQ)
);

CREATE TABLE [dbo].[MP0006](
	[MP0006_SEQ] [int] IDENTITY(1,1) NOT NULL,
	[MP0006_CODE] [varchar](50) NULL,
	[MP0006_VALUE] [varchar](200) NULL
);

CREATE TABLE [dbo].[MP0008](
	[MP0008_SEQ] [int] IDENTITY(1,1) NOT FOR REPLICATION NOT NULL,
	[MP0008_DEPARTMENT_ID] [int] NULL,
	[MP0008_POSITION_ID] [int] NULL
);

CREATE TABLE [dbo].[MP0009](
	[MP0009_SEQ] [int] IDENTITY(1,1) NOT FOR REPLICATION NOT NULL,
	[MP0009_POSITION_NAME_E] [varchar](200) NULL,
	[MP0009_POSITION_NAME_C] [varchar](200) NULL,
	[MP0009_POSITION_STATUS] [varchar](5) NULL,
	[MP0009_CREATE_USER] [varchar](10) NULL,
	[MP0009_CREATE_DATETIME] [datetime] NULL,
	[MP0009_EDIT_USER] [varchar](10) NULL,
	[MP0009_EDIT_DATETIME] [datetime] NULL
);

CREATE TABLE [dbo].[MP0010](
	id int IDENTITY(1,1) not null,
	[MP0010_DATETIME] [varchar](30) NULL,
	[MP0010_START_TIME] [varchar](30) NULL,
	[MP0010_END_TIME] [varchar](30) NULL,
	[MP0010_LUNCH_TIME] [varchar](10) NULL,
	[MP0010_TOTAL_TIME] [varchar](10) NULL,
	[MP0010_OTHER1] [varchar](10) NULL,
	[MP0010_OTHER2] [varchar](10) NULL,
	[workTimePatternId] [int],
	primary key (id)
);

CREATE TABLE [dbo].[MP0011](
	[MP0011_SEQ] [varchar](50) NOT NULL,
	[MP0011_SYS_CODE] [varchar](30) NULL,
	[MP0011_USR_ID] [varchar](10) NULL,
	[MP0011_USR_NAME] [varchar](100) NULL,
	[MP0011_LOGIN_TIME] [datetime] NULL,
	[MP0011_LOGIN_IP] [varchar](20) NULL,
	[MP0011_MEMO] [varchar](2000) NULL,
	primary key (MP0011_SEQ)
);

CREATE TABLE [dbo].[MP1001](
	[MP1001_EMPLOYEE_NUM] [varchar](10) NOT NULL,
	[MP1001_EMPLOYEE_ID] [varchar](30) NULL,
	[MP1001_SURNAME] [varchar](50) NULL,
	[MP1001_FIRSTNAME] [varchar](50) NULL,
	[MP1001_PREFERED_NAME] [varchar](50) NULL,
	[MP1001_VISA_TYPE] [varchar](1) NULL,
	[MP1001_DEPARTMENT_ID] [varchar](10) NULL,
	[MP1001_POSITION] [varchar](5) NULL,
	[MP1001_GENDER] [varchar](1) NULL,
	[MP1001_RELIGION] [varchar](30) NULL,
	[MP1001_NATIONALITY] [varchar](30) NULL,
	[MP1001_BIRTHDAY] [datetime] NULL,
	[MP1001_MARRIAGE_STATUS] [varchar](1) NULL,
	[MP1001_RACE] [varchar](30) NULL,
	[MP1001_MOBILE_PHONE] [varchar](30) NULL,
	[MP1001_TELEPHONE] [varchar](30) NULL,
	[MP1001_OTHER_CONTACT] [varchar](30) NULL,
	[MP1001_EMAIL] [varchar](50) NULL,
	[MP1001_COMPANY_EMAIL] [varchar](50) NULL,
	[MP1001_DEGREE_LEVEL] [varchar](2) NULL,
	[MP1001_ENTRY_DATE] [datetime] NULL,
	[MP1001_STATUS] [varchar](1) NULL,
	[MP1001_ANNUAL_STATUS] [varchar](2) NULL,
	[MP1001_RESIGN_DATE] [datetime] NULL,
	[MP1001_RESIGN_REASON] [nvarchar](4000) NULL,
	[MP1001_RESIGN_TYPE] [nvarchar](2) NULL,
	[MP1001_PICTURE] [varbinary](max) NULL,
	[MP1001_PICTURE_NAME] [varchar](500) NULL,
	[MP1001_PHYSICAL_ADDRESS] [varchar](200) NULL,
	[MP1001_PASSWORD] [varchar](100) NULL,
	[MP1001_PASSWORD_DATE] [datetime] NULL,
	[MP1001_GROUP] [varchar](30) NULL,
	[MP1001_RECTIFY_TIMES] [varchar](5) NULL,
	[MP1001_FREE_MONEY] [int] NULL,
	[MP1001_CHG_TIME] [datetime] NULL,
	[MP1001_CHG_EMPLOYE] [varchar](30) NULL,
	[MP1001_APPRASIER] [varchar](10) NULL,
	[MP1001_EFFECTIVE_DATE_YEAR] [varchar](30) NULL,
	[MP1001_EFFECTIVE_DATE_MONTH] [varchar](30) NULL,
	[MP1001_PAYROLL_NUM] [varchar](30) NULL,
	[MP1001_CREATE_USER] [varchar](10) NULL,
	[MP1001_CREATE_DATE] [datetime] NULL,
	[MP1001_EDIT_USER] [varchar](10) NULL,
	[MP1001_EDIT_DATE] [datetime] NULL,
	primary key (MP1001_EMPLOYEE_NUM)
);

CREATE TABLE [dbo].[MP1002](
	[MP1002_SEQ] [int] IDENTITY(1,1) NOT NULL,
	[MP1002_EMPLOYEE_NUM] [varchar](10) NOT NULL,
	[MP1002_INSTITUTION_TYPE] [varchar](1) NULL,
	[MP1002_INSTITUTION_NAME] [varchar](500) NULL,
	[MP1002_QUALIFICATION_LEV] [varchar](2) NULL,
	[MP1002_MAJOR] [varchar](200) NULL,
	[MP1002_START_DATETIME] [datetime] NULL,
	[MP1002_FINISH_DATETIME] [datetime] NULL,
	[MP1002_CREATE_USER] [varchar](10) NULL,
	[MP1002_CREATE_DATETIME] [datetime] NULL,
	[MP1002_EDIT_USER] [varchar](10) NULL,
	[MP1002_EDIT_DATETIME] [datetime] NULL,
	primary key (MP1002_SEQ)
);

CREATE TABLE [dbo].[MP1003](
	[MP1003_SEQ] [int] IDENTITY(1,1) NOT NULL,
	[MP1003_EMPLOYEE_NUM] [varchar](10) NOT NULL,
	[MP1003_COMPANY_NAME] [varchar](200) NULL,
	[MP1003_DEPARTMENT_ID] [varchar](100) NULL,
	[MP1003_POSITION] [varchar](200) NULL,
	[MP1003_ENTRY_DATETIME] [datetime] NULL,
	[MP1003_TERMINATION_DATETIME] [datetime] NULL,
	[MP1003_JOB_DESC] [varchar](1000) NULL,
	[MP1003_TERMINATION_REASON] [varchar](1000) NULL,
	[MP1003_CONTACT_PERSON_INFO] [varchar](1000) NULL,
	[MP1003_CREATE_USER] [varchar](10) NULL,
	[MP1003_CREATE_DATETIME] [datetime] NULL,
	[MP1003_EDIT_USER] [varchar](10) NULL,
	[MP1003_EDIT_DATETIME] [datetime] NULL,
	primary key (MP1003_SEQ)
);

CREATE TABLE [dbo].[MP1004](
	[MP1004_SEQ] [int] IDENTITY(1,1) NOT NULL,
	[MP1004_EMPLOYEE_NUM] [varchar](10) NOT NULL,
	[MP1004_DEPARTMENT_CODE] [varchar](10) NULL,
	[MP1004_POSITION] [varchar](5) NULL,
	[MP1004_TRANSFER_DATETIME] [datetime] NULL,
	[MP1004_JOB_DESC] [varchar](500) NULL,
	[MP1004_DEPARTMENT_CODE_OLD] [varchar](10) NULL,
	[MP1004_POSITION_OLD] [varchar](5) NULL,
	[MP1004_CREATE_USER] [varchar](10) NULL,
	[MP1004_CREATE_DATETIME] [datetime] NULL,
	[MP1004_EDIT_USER] [varchar](10) NULL,
	[MP1004_EDIT_DATETIME] [datetime] NULL
);

CREATE TABLE [dbo].[MP1005](
	[MP1005_SEQ] [int] IDENTITY(1,1) NOT NULL,
	[MP1005_EMPLOYEE_NUM] [varchar](10) NOT NULL,
	[MP1005_NAME] [varchar](50) NULL,
	[MP1005_RELATIONSHIP] [varchar](100) NULL,
	[MP1005_TELEPHONE] [varchar](30) NULL,
	[MP1005_EMAIL] [varchar](50) NULL,
	[MP1005_PHYSICAL_ADDRESS] [varchar](200) NULL,
	[MP1005_CREATE_USER] [varchar](10) NULL,
	[MP1005_CREATE_DATETIME] [datetime] NULL,
	[MP1005_EDIT_USER] [varchar](10) NULL,
	[MP1005_EDIT_DATETIME] [datetime] NULL,
	primary key (MP1005_SEQ)
);

CREATE TABLE [dbo].[MP1006](
	[MP1006_SEQ] [int] IDENTITY(1,1) NOT NULL,
	[MP1006_EMPLOYEE_NUM] [varchar](10) NOT NULL,
	[MP1006_RESIGN_TYPE] [varchar](1) NULL,
	[MP1006_RESIGN_DATETIME] [datetime] NULL,
	[MP1006_REASON] [varchar](200) NULL,
	[MP1006_CREATE_USER] [varchar](10) NULL,
	[MP1006_CREATE_DATETIME] [datetime] NULL,
	[MP1006_EDIT_USER] [varchar](10) NULL,
	[MP1006_EDIT_DATETIME] [datetime] NULL
);

CREATE TABLE [dbo].[MP1007](
	[MP1007_SEQ] [int] IDENTITY(1,1) NOT NULL,
	[MP1007_EMPLOYEE_NUM] [varchar](10) NOT NULL,
	[MP1007_RESRORATION_TYPE] [varchar](1) NULL,
	[MP1007_RESRORATION_DATETIME] [datetime] NULL,
	[MP1007_REASON] [varchar](200) NULL,
	[MP1007_CREATE_USER] [varchar](10) NULL,
	[MP1007_CREATE_DATETIME] [datetime] NULL,
	[MP1007_EDIT_USER] [varchar](10) NULL,
	[MP1007_EDIT_DATETIME] [datetime] NULL
); 

CREATE TABLE [dbo].[MP1008](
	[MP1008_ID] [varchar](30) NULL,
	[MP1008_NAME] [varchar](100) NULL,
	[MP1008_PHONE] [varchar](50) NULL,
	[MP1008_INTERVIEW_DATETIME] [datetime] NULL,
	[MP1008_INTERVIEWER] [varchar](20) NULL,
	[MP1008_CV_PATH] [varchar](1000) NULL
);

CREATE TABLE [dbo].[MP1009](
	[MP1009_SEQ] [int] IDENTITY(1,1) NOT NULL,
	[MP1009_EMPLOYEE_NUM] [varchar](50) NULL,
	[MP1009_DOCUMENT_NAME] [varchar](100) NULL,
	[MP1009_PATH] [varchar](1000) NULL,
	[MP1009_UPLOAD_TIME] [datetime] NULL,
	[MP1009_UPLOADER] [varchar](10) NULL
);

CREATE TABLE [dbo].[MP1010](
	[MP1010_EMPLOYEE_NUM] [nvarchar](10) NOT NULL,
	[MP1010_EMPLOYEE_ID] [nvarchar](30) NULL,
	[MP1010_FIRST_NAME] [nvarchar](100) NULL,
	[MP1010_PREFERED_NAME] [nvarchar](100) NULL,
	[MP1010_DEPARTMENT] [nvarchar](10) NULL,
	[MP1010_GENDER] [nvarchar](5) NULL,
	[MP1010_VISA_TYPE] [nvarchar](5) NULL,
	[MP1010_TEL] [nvarchar](30) NULL,
	[MP1010_NATIONAL] [nvarchar](50) NULL,
	[MP1010_PASSWORD] [nvarchar](30) NULL,
	[MP1010_STATUS] [nvarchar](5) NULL,
	[MP1010_PAYROLL_NUM] [nchar](10) NULL,
	[MP1010_CREATE_USER] [nvarchar](5) NULL,
	[MP1010_CREATE_DATETIME] [datetime] NULL,
	[MP1010_EDIT_USER] [nvarchar](5) NULL,
	[MP1010_EDIT_DATETIME] [datetime] NULL,
	primary key (MP1010_EMPLOYEE_NUM)
);

CREATE TABLE [dbo].[MP2001](
	[MP2001_NUM] [varchar](12) NOT NULL,
	[MP2001_EMPLOYEE_NUM] [varchar](10) NOT NULL,
	[MP2001_ACTING_APPLICATION_PERSON] [varchar](10) NULL,
	[MP2001_APPLIY_TYPE] [varchar](20) NULL,
	[MP2001_DAYS] [varchar](10) NULL,
	[MP2001_FROM_DATETIME] [datetime] NULL,
	[MP2001_TO_DATETIME] [datetime] NULL,
	[MP2001_ACTING_PERSON] [varchar](50) NULL,
	[MP2001_LEAVE_TYPE] [varchar](1) NULL,
	[MP2001_APPROVAL] [varchar](1) NULL,
	[MP2001_STATUS] [varchar](1) NULL,
	[MP2001_COMMENT] [nvarchar](2000) NULL,
	[MP2001_MAJOR_SEQ] [varchar](20) NULL,
	[MP2001_CREATE_USER] [varchar](10) NULL,
	[MP2001_CREATE_DATETIME] [datetime] NULL,
	[MP2001_EDIT_USER] [varchar](10) NULL,
	[MP2001_EDIT_DATETIME] [datetime] NULL,
	[MP2001_APPROVAL_USER] [varchar](10) NULL,
	[MP2001_APPROVAL_DATETIME] [datetime] NULL,
	primary key (MP2001_NUM)
)

CREATE TABLE [dbo].[MP2002](
	[MP2002_EMPLOYEE_NUM] [varchar](10) NOT NULL,
	[MP2002_YEAR] [varchar](4) NOT NULL,
	[MP2002_ANNUAL] [varchar](10) NULL,
	[MP2002_ANNUAL_T] [varchar](10) NULL,
	[MP2002_ANNUAL_MAX] [varchar](10) NULL,
	[MP2002_SICK] [varchar](10) NULL,
	[MP2002_SICK_T] [varchar](10) NULL,
	[MP2002_FAMILY_RESP] [varchar](10) NULL,
	[MP2002_FAMILY_RESP_T] [varchar](10) NULL,
	[MP2002_MATERNITY] [varchar](10) NULL,
	[MP2002_MATERNITY_T] [varchar](10) NULL,
	[MP2002_STUDY] [varchar](10) NULL,
	[MP2002_STUDY_T] [varchar](10) NULL,
	primary key (MP2002_EMPLOYEE_NUM, MP2002_YEAR)
);

CREATE TABLE [dbo].[MP2003](
	[MP2003_EMPLOYEE_NUM] [varchar](10) NOT NULL,
	[MP2003_DATETIME] [datetime] NOT NULL,
	[MP2003_START_TIME] [datetime] NULL,
	[MP2003_FINISH_TIME] [datetime] NULL,
	[MP2003_START_TIME_DOOR] [varchar](30) NULL,
	[MP2003_FINISH_TIME_DOOR] [varchar](30) NULL,
	[MP2003_START_TIME_E] [datetime] NULL,
	[MP2003_FINISH_TIME_E] [datetime] NULL,
	[MP2003_COMMENT] [varchar](100) NULL,
	[MP2003_DES] [varchar](100) NULL,
	[MP2003_STATUS] [varchar](10) NULL,
	[MP2003_EDIT_USER] [varchar](10) NULL,
	[MP2003_EDIT_DATETIME] [datetime] NULL,
	primary key (MP2003_EMPLOYEE_NUM, MP2003_DATETIME)
);

CREATE TABLE [dbo].[MP2004](
	[MP2004_SEQ] [varchar](20) NOT NULL,
	[MP2004_EMPLOYEE_NUM] [varchar](10) NOT NULL,
	[MP2004_MAJOR_NAME] [varchar](200) NULL,
	[MP2004_TIME] [varchar](20) NULL,
	[MP2004_STATUS] [varchar](10) NULL,
	primary key (MP2004_SEQ)
);

CREATE TABLE [dbo].[MP2007](
	[MP2007_SEQ] [int] IDENTITY(1,1) NOT NULL,
	[MP2007_EMPLOYEE_NUM] [varchar](10) NULL,
	[MP2007_DAYS] [varchar](5) NULL,
	[MP2007_STATUS] [varchar](5) NULL,
	[MP2007_TYPE] [varchar](5) NULL,
	[MP2007_MAJOR_NAME] [varchar](200) NULL,
	[MP2007_CREATE_USER] [varchar](10) NULL,
	[MP2007_CREATE_DATETIME] [datetime] NULL,
	[MP2007_EDIT_USER] [varchar](10) NULL,
	[MP2007_EDIT_DATETIME] [datetime] NULL
);

CREATE TABLE [dbo].[MP2008](
	[MP2008_NUM] [nvarchar](30) NOT NULL,
	[MP2008_EMPLOYEE_NUM] [nvarchar](10) NULL,
	[MP2008_DEPARTMENT] [nvarchar](10) NULL,
	[MP2008_DATE] [nvarchar](10) NULL,
	[MP2008_HOURS_TOTAL] [int] NULL,
	[MP2008_HOURS] [nvarchar](10) NULL,
	[MP2008_FROM_DATETIME] [nvarchar](2) NULL,
	[MP2008_TO_DATETIME] [nvarchar](2) NULL,
	[MP2008_FROM_MINUTE] [nvarchar](2) NULL,
	[MP2008_TO_MINUTE] [nvarchar](2) NULL,
	[MP2008_REASON] [nvarchar](2000) NULL,
	[MP2008_APP_STATUS] [nvarchar](1) NULL,
	[MP2008_DATA_STATUS] [nvarchar](1) NULL,
	[MP2008_COMMENT] [nvarchar](2000) NULL,
	[MP2008_RATING] [nvarchar](10) NULL,
	[MP2008_CREATE_USER] [char](6) NULL,
	[MP2008_CREATE_DATETIME] [datetime] NULL,
	[MP2008_EDIT_USER] [char](6) NULL,
	[MP2008_EDIT_DATETIME] [datetime] NULL,
	[MP2008_APPROVAL_USER] [char](6) NULL,
	[MP2008_APPROVAL_DATETIME] [datetime] NULL,
	primary key (MP2008_NUM) 
);

CREATE TABLE [dbo].[MP2009](
	[MP2009_NUM] [nvarchar](30) NOT NULL,
	[MP2009_EMPLOYEE_NUM] [nvarchar](10) NOT NULL,
	[MP2009_YEAR] [nvarchar](10) NOT NULL,
	[MP2009_MONTH] [nvarchar](10) NOT NULL,
	[MP2009_ANNUAL] [decimal](18, 6) NULL,
	[MP2009_SICK] [decimal](18, 6) NULL,
	[MP2009_FAMILY_RESP] [decimal](18, 6) NULL,
	[MP2009_STUDY] [decimal](18, 6) NULL,
	[MP2009_MATERNITY] [decimal](18, 6) NULL,
	[MP2009_UNPAID] [decimal](18, 6) NULL,
	[MP2009_OFFICIAL_BUSINESS] [decimal](18, 6) NULL,
	[MP2009_LEAVE_EARLY] [decimal](18, 6) NULL,
	[MP2009_LATE] [decimal](18, 6) NULL,
	[MP2009_ABSENTEEISM] [decimal](18, 6) NULL,
	primary key (MP2009_NUM)
); 

CREATE TABLE [dbo].[MP2010](
	[MP2010_ID] [int] IDENTITY(1,1) NOT NULL,
	[MP2010_EMPLOYEE_NUM] [nvarchar](10) NULL,
	[MP2010_DATE] [datetime] NULL,
	[MP2010_FROM_DATETIME] [datetime] NULL,
	[MP2010_END_DATETIME] [datetime] NULL,
	[MP2010_TYPE] [nvarchar](2) NULL,
	[MP2010_BRANCH_SITE] [nvarchar](30) NULL,
	primary key (MP2010_ID)
);

CREATE TABLE [dbo].[MP4001](
	[MP4001_NUM] [varchar](10) NOT NULL,
	[MP4001_NAME] [varchar](500) NULL,
	[MP4001_PARENT_TYPE] [varchar](10) NULL,
	[MP4001_STATUS] [varchar](5) NULL,
	[MP4001_RELATED_DEPARTMENT] [varchar](10) NULL,
	[MP4001_DES] [varchar](2000) NULL,
	[MP4001_TYPE] [varchar](10) NULL
);

CREATE TABLE [dbo].[MP4002](
	[MP4002_NUM] [varchar](10) NOT NULL,
	[MP4002_NAME_E] [varchar](500) NULL,
	[MP4002_NAME_C] [varchar](500) NULL,
	[MP4002_STATUS] [varchar](5) NULL
);

CREATE TABLE [dbo].[MP4003](
	[MP4003_SEQ] [int] IDENTITY(1,1) NOT NULL,
	[MP4003_DEPARTMENT_ID] [varchar](10) NULL,
	[MP4003_ACCOUNTING_NUM] [varchar](10) NULL,
	[MP4003_AMOUNT] [decimal](18, 2) NULL,
	[MP4003_AMOUNT2] [decimal](18, 2) NULL,
	[MP4003_AMOUNT3] [decimal](18, 2) NULL,
	[MP4003_AMOUNT_MAX] [decimal](18, 2) NULL,
	[MP4003_STATUS] [varchar](2) NULL,
	[MP4003_YEAR] [varchar](4) NULL,
	[MP4003_MONTH] [varchar](2) NULL
);

CREATE TABLE [dbo].[MP4003_TEMPLATE](
	[MP4003_TEMPLATE_SEQ] [int] IDENTITY(1,1) NOT NULL,
	[MP4003_TEMPLATE_DEPARTMENT_ID] [varchar](10) NULL,
	[MP4003_TEMPLATE_ACCOUNTING_NUM] [varchar](10) NULL,
	[MP4003_TEMPLATE_AMOUNT] [decimal](18, 2) NULL,
	[MP4003_TEMPLATE_AMOUNT2] [decimal](18, 2) NULL,
	[MP4003_TEMPLATE_AMOUNT3] [decimal](18, 2) NULL,
	[MP4003_TEMPLATE_AMOUNT_MAX] [decimal](18, 2) NULL,
	[MP4003_TEMPLATE_STATUS] [varchar](2) NULL,
	[MP4003_TEMPLATE_YEAR] [varchar](4) NULL,
	[MP4003_TEMPLATE_MONTH] [varchar](2) NULL
);

CREATE TABLE [dbo].[MP4004](
	[MP4004_SEQ] [int] IDENTITY(1,1) NOT NULL,
	[MP4004_KEY] [varchar](10) NULL,
	[MP4004_AMOUNT] [decimal](18, 2) NULL,
	[MP4004_REASON] [varchar](3000) NULL,
	[MP4004_DATETIME] [datetime] NULL,
	[MP4004_USER] [varchar](10) NULL,
	[MP4004_STATUS] [varchar](5) NULL
);

CREATE TABLE [dbo].[MP7001](
	[MP7001_SEQ] [varchar](20) NOT NULL,
	[MP7001_TITLE] [varchar](4000) NOT NULL,
	[MP7001_SUB_TITLE] [varchar](4000) NULL,
	[MP7001_COMMENT] [varchar](4000) NULL,
	[MP7001_STATUS] [varchar](2) NULL,
	[MP7001_CREATOR] [varchar](10) NULL,
	[MP7001_CREATE_TIME] [datetime] NULL,
	[MP7001_EDITOR] [varchar](10) NULL,
	[MP7001_EDIT_TIME] [datetime] NULL,
	primary key (MP7001_SEQ)
);

CREATE TABLE [dbo].[MP7002](
	[MP7002_SEQ] [varchar](30) NOT NULL,
	[MP7002_TITLE] [varchar](1000) NOT NULL,
	[MP7002_YEAR] [varchar](4) NOT NULL,
	[MP7002_MONTH] [varchar](2) NOT NULL,
	[MP7002_COMMENT] [varchar](4000) NULL,
	[MP7002_CREATOR] [varchar](10) NULL,
	[MP7002_STATUS] [varchar](2) NULL,
	[MP7002_EXECUTE_STATUS] [varchar](2) NULL,
	[MP7002_FINISH_STATUS] [varchar](2) NULL,
	[MP7002_TYPE] [varchar](2) NULL
);

CREATE TABLE [dbo].[MP7003](
	[MP7003_SEQ] [varchar](30) NOT NULL,
	[MP7003_MASTER_KEY] [varchar](30) NOT NULL,
	[MP7003_EXAM_CODE] [varchar](20) NOT NULL,
	[MP7003_WEIGHTAGE] [varchar](10) NULL
);

CREATE TABLE [dbo].[MP7004](
	[MP7004_SEQ] [varchar](20) NOT NULL,
	[MP7004_EMPLOYEE_NUM] [varchar](10) NOT NULL,
	[MP7004_JOB_TITLE] [varchar](10) NOT NULL,
	[MP7004_DEPARTMENT] [varchar](10) NOT NULL,
	[MP7004_MANAGER] [varchar](10) NOT NULL,
	[MP7004_REVIEW_PERIOD] [varchar](10) NOT NULL,
	[MP7004_SCORES] [varchar](5) NOT NULL,
	[MP7004_COMMENT] [varchar](4000) NULL,
	[MP7004_STATUS] [varchar](2) NULL,
	[MP7004_APPRAISER] [varchar](10) NULL,
	[MP7004_APPRAISER_DESIGNATION] [varchar](30) NULL
);

CREATE TABLE [dbo].[MP7005](
	[MP7005_SEQ] [varchar](20) NOT NULL,
	[MP7005_MASTER_KEY] [varchar](20) NOT NULL,
	[MP7005_EXAM_CODE] [varchar](20) NOT NULL,
	[MP7005_SCORES] [varchar](5) NOT NULL
);

CREATE TABLE [dbo].[MP7006](
	[MP7006_SEQ] [varchar](20) NOT NULL,
	[MP7006_EMPLOYEE_NUM] [varchar](10) NULL,
	[MP7006_EFFECTIVE_DATE_LAST_APPRAISAL] [datetime] NULL,
	[MP7006_APPRAISER] [varchar](10) NULL,
	[MP7006_REVIEWER] [varchar](10) NULL,
	[MP7006_DESIGNATION] [varchar](200) NULL,
	[MP7006_DEPARTMENT] [varchar](10) NULL,
	[MP7006_CURRENT_APPRAISAL_CYCLE] [varchar](10) NULL,
	[MP7006_APPRAISER_DESIGNATION] [varchar](200) NULL,
	[MP7006_REVIEWER_DESIGNATION] [varchar](200) NULL,
	[MP7006_COMPETENCE_TOTAL_SCORES] [varchar](10) NULL,
	[MP7006_GRAND_TOTAL_SCORES] [varchar](10) NULL,
	[MP7006_STATUS] [varchar](2) NULL,
	[MP7006_TOTAL_WEIGHTAGE] [varchar](10) NULL,
	[MP7006_EFFECTIVE_DATE_APPRAISAL] [datetime] NULL
);

CREATE TABLE [dbo].[MP7007](
	[MP7007_SEQ] [varchar](20) NOT NULL,
	[MP7007_MASTER_KEY] [varchar](20) NOT NULL,
	[MP7007_EXAM_CODE] [varchar](20) NOT NULL,
	[MP7007_SELF_SCORE] [varchar](10) NULL,
	[MP7007_MANAGER_SCORE] [varchar](10) NULL,
	[MP7007_AGREED_SCORE] [varchar](10) NULL,
	[MP7007_REMARKS] [varchar](4000) NULL,
	[MP7007_WEIGHTAGE] [varchar](10) NULL,
	[MP7007_FINAL_SCORE] [varchar](10) NULL
);

CREATE TABLE [dbo].[MP7008](
	[MP7008_SEQ] [varchar](30) NOT NULL,
	[MP7008_PLAN_SEQ] [varchar](30) NOT NULL,
	[MP7008_EMPLOYEE_NUM] [varchar](10) NOT NULL
);

CREATE TABLE [dbo].[MP8001](
	[MP8001_SEQ] [varchar](20) NOT NULL,
	[MP8001_TITLE] [varchar](4000) NOT NULL,
	[MP8001_SUB_TITLE] [varchar](4000) NULL,
	[MP8001_COMMENT] [varchar](4000) NULL,
	[MP8001_STATUS] [varchar](2) NULL,
	[MP8001_CREATOR] [varchar](10) NULL,
	[MP8001_CREATE_TIME] [datetime] NULL,
	[MP8001_EDITOR] [varchar](10) NULL,
	[MP8001_EDIT_TIME] [datetime] NULL,
	primary key (MP8001_SEQ)
);

CREATE TABLE [dbo].[MP8002](
	[MP8002_SEQ] [varchar](30) NOT NULL,
	[MP8002_TITLE] [varchar](1000) NOT NULL,
	[MP8002_DEPARTMENT_NUM] [varchar](4) NULL,
	[MP8002_YEAR] [varchar](4) NOT NULL,
	[MP8002_MONTH] [varchar](2) NOT NULL,
	[MP8002_COMMENT] [varchar](4000) NULL,
	[MP8002_CREATOR] [varchar](10) NULL,
	[MP8002_STATUS] [varchar](2) NULL,
	[MP8002_EXECUTE_STATUS] [varchar](2) NULL,
	[MP8002_FINISH_STATUS] [varchar](2) NULL,
	primary key (MP8002_SEQ)
);

CREATE TABLE [dbo].[MP8003](
	[MP8003_SEQ] [varchar](30) NOT NULL,
	[MP8003_MASTER_KEY] [varchar](30) NOT NULL,
	[MP8003_EXAM_CODE] [varchar](20) NOT NULL,
	[MP8003_WEIGHTAGE] [varchar](10) NULL,
	primary key (MP8003_SEQ)
);

CREATE TABLE [dbo].[MP8004](
	[MP8004_SEQ] [varchar](30) NOT NULL,
	[MP8004_PLAN_SEQ] [varchar](30) NOT NULL,
	[MP8004_DEPARTMENT_NUM] [varchar](10) NOT NULL,
	primary key (MP8004_SEQ)
);

CREATE TABLE [dbo].[MP8005](
	[MP8005_SEQ] [varchar](20) NOT NULL,
	[MP8005_EMPLOYEE_NUM] [varchar](10) NULL,
	[MP8005_JOB_TITLE] [varchar](10) NULL,
	[MP8005_DEPARTMENT] [varchar](10) NULL,
	[MP8005_MANAGER] [varchar](10) NULL,
	[MP8005_REVIEW_PERIOD] [char](10) NULL,
	[MP8005_SCORES] [varchar](5) NULL,
	[MP8005_COMMENT] [varchar](4000) NULL,
	[MP8005_STATUS] [varchar](2) NULL,
	[MP8005_APPRAISER] [varchar](10) NULL,
	[MP8005_APPRAISER_DESIGNATION] [varchar](30) NULL,
	primary key (MP8005_SEQ)
);

CREATE TABLE [dbo].[MP8006](
	[MP8006_SEQ] [varchar](20) NOT NULL,
	[MP8006_MASTER_KEY] [varchar](20) NOT NULL,
	[MP8006_EXAM_CODE] [varchar](20) NOT NULL,
	[MP8006_SCORES] [varchar](5) NOT NULL,
	primary key (MP8006_SEQ)
);

CREATE TABLE [dbo].[JE0101](
	[JE0101_PROPERTY] [nvarchar](50) NULL,
	[JE0101_VALUE] [nvarchar](100) NULL,
	[JE0101_TYPE] [nvarchar](15) NULL
);

CREATE TABLE [dbo].[JE0201](
	[JE0201_ROOM_NAME] [nvarchar](50) NOT NULL,
	[JE0201_ROOM_FLOOR] [nvarchar](30) NULL,
	[JE0201_ROOM_TYPE] [nvarchar](20) NULL,
	[JE0201_ROOM_DES] [nvarchar](100) NULL,
	[JE0201_STATUS] [nvarchar](2) NULL
);

CREATE TABLE [dbo].[JE0202](
	[JE0202_SEQ] [nvarchar](30) NOT NULL,
	[JE0202_CASE_CODE] [nvarchar](30) NULL,
	[JE0202_ROOM_CODE] [nvarchar](30) NULL,
	[JE0202_ROOM_NAME] [nvarchar](30) NULL,
	[JE0202_ROOM_FLOOR] [nvarchar](30) NULL,
	[JE0202_DATE] [datetime] NULL,
	[JE0202_FROM_DATETIME] [datetime] NULL,
	[JE0202_END_DATETIME] [datetime] NULL,
	[JE0202_USER_NUM] [nvarchar](10) NULL,
	[JE0202_CREATE_DATETIME] [datetime] NULL,
	[JE0202_USER_TYPE] [nvarchar](10) NULL,
	[JE0202_DES] [nvarchar](200) NULL,
	[JE0202_STATUS] [nvarchar](2) NULL
);

/*work time patter tables*/
create table DetailDayWorkTime (
        id int identity not null,
        name varchar(40),
        fromTime datetime,
        toTime datetime,
        eachCircleDayId int,
        primary key (id)
);

create table EachCircleDay (
        id int identity not null,
        daySeq int not null,
        description varchar(255),
        name varchar(40),
        workTimePatternId int,
        overTimeRate float,  /*over time rate*/
        /* next version //
         *    calculate special day,
         * 	  calculate public holiday,
         *    ignore public holiday~
         */
        primary key (id)
);

create table WorkTimePattern (
        id int identity not null,
        name varchar(40),
        circleDays int,
        description varchar(255),
        calPubHolidayFlag bit,
        calSpecialDayFlag bit,
        applyLeaveIgnorePublicHolidayFlag bit,
        initialAnnualLeaveDays float,
        addAnnualHoursPM float,
        initialSickLeaveDays float,
        addSickHoursPM float,
        dayWorkHours float,
        primary key (id)
);

create table EmpWorkTimePattern_R(
	id int identity not null,
	employeeCode varchar(10) not null,
	workTimePatterId int not null,
	startDate datetime,
	initialCircleDayIdx int,
	primary key (id)
);
		
create table StandardWorkTime(
	id int identity not null,
	employeeCode varchar(10) not null,
	dayDate datetime not null,
	circleDayId int, /*for which day in circle*/
	stdClockInTime datetime,
	stdClockOutTime datetime,
	manuallyChangeFlag bit, /*change flag*/
	manuallyChangeLogId varchar(50),
	comments varchar(40),
	lateMinutes int, /*for save late data */
	earlyMinutes int, /* for save late data */
	primary key (id)
);

insert into MP0006(MP0006_CODE, MP0006_VALUE) values('LATEST_CHECKTIME', '2014-08-12 00:00:01'); /*this part for checktime compare*/

/*for sql server import data wizard*/
alter table DetailDayWorkTime drop constraint FK_DetailDayWorkTime;
alter table EachCircleDay drop constraint FK_EachCircleDay;
alter table EmpWorkTimePattern_R drop constraint FK_Emp_R;
alter table EmpWorkTimePattern_R drop constraint FK_WorkTimePatternId;
alter table StandardWorkTime drop constraint FK_StandardWorkTime;
alter table StandardWorkTime drop constraint FK_StandardWorkTime_CircleDay;
alter table StandardWorkTime drop constraint FK_StandardWorkTime_1;

alter table DetailDayWorkTime 
	add constraint FK_DetailDayWorkTime
	foreign key (eachCircleDayId) 
	references EachCircleDay;

alter table EachCircleDay 
    add constraint FK_EachCircleDay
    foreign key (workTimePatternId) 
    references WorkTimePattern;

alter table EmpWorkTimePattern_R
	add constraint FK_Emp_R
	foreign key (employeeCode)
	references MP1001(MP1001_EMPLOYEE_NUM);

alter table EmpWorkTimePattern_R 	
	add constraint FK_WorkTimePatternId
	foreign key (workTimePatterId)
	references WorkTimePattern(id);
	
alter table StandardWorkTime
	add constraint FK_StandardWorkTime
	foreign key (employeeCode)
	references MP1001(MP1001_EMPLOYEE_NUM);
	
alter table StandardWorkTime
	add constraint FK_StandardWorkTime_CircleDay 
	foreign key (circleDayId) 
	references EachCircleDay(id); 

alter table StandardWorkTime
	add constraint FK_StandardWorkTime_1
	foreign key (manuallyChangeLogId) 
	references MP0011(MP0011_SEQ);

alter table MP0010
	add constraint FK_MP0010_0
	foreign key (workTimePatternId) 
	references WorkTimePattern(id);