USE [CVSystem]
GO
IF  EXISTS (SELECT * FROM sys.check_constraints WHERE object_id = OBJECT_ID(N'[dbo].[CK_Educations]') AND parent_object_id = OBJECT_ID(N'[dbo].[Educations]'))
ALTER TABLE [dbo].[Educations] DROP CONSTRAINT [CK_Educations]
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_WorkExps_CVs]') AND parent_object_id = OBJECT_ID(N'[dbo].[WorkExps]'))
ALTER TABLE [dbo].[WorkExps] DROP CONSTRAINT [FK_WorkExps_CVs]
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_Students_Groups]') AND parent_object_id = OBJECT_ID(N'[dbo].[Students]'))
ALTER TABLE [dbo].[Students] DROP CONSTRAINT [FK_Students_Groups]
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_Sertificats_CVs]') AND parent_object_id = OBJECT_ID(N'[dbo].[Sertificats]'))
ALTER TABLE [dbo].[Sertificats] DROP CONSTRAINT [FK_Sertificats_CVs]
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_ProgramLanguagesCVs_ProgramLanguages]') AND parent_object_id = OBJECT_ID(N'[dbo].[ProgramLanguagesCVs]'))
ALTER TABLE [dbo].[ProgramLanguagesCVs] DROP CONSTRAINT [FK_ProgramLanguagesCVs_ProgramLanguages]
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_ProgramLanguagesCVs_CVs]') AND parent_object_id = OBJECT_ID(N'[dbo].[ProgramLanguagesCVs]'))
ALTER TABLE [dbo].[ProgramLanguagesCVs] DROP CONSTRAINT [FK_ProgramLanguagesCVs_CVs]
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_LanguagesCVs_Languages]') AND parent_object_id = OBJECT_ID(N'[dbo].[LanguagesCVs]'))
ALTER TABLE [dbo].[LanguagesCVs] DROP CONSTRAINT [FK_LanguagesCVs_Languages]
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_LanguagesCVs_CVs]') AND parent_object_id = OBJECT_ID(N'[dbo].[LanguagesCVs]'))
ALTER TABLE [dbo].[LanguagesCVs] DROP CONSTRAINT [FK_LanguagesCVs_CVs]
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_Groups_Faculties]') AND parent_object_id = OBJECT_ID(N'[dbo].[Groups]'))
ALTER TABLE [dbo].[Groups] DROP CONSTRAINT [FK_Groups_Faculties]
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_Educations_CVs]') AND parent_object_id = OBJECT_ID(N'[dbo].[Educations]'))
ALTER TABLE [dbo].[Educations] DROP CONSTRAINT [FK_Educations_CVs]
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_CVs_Students]') AND parent_object_id = OBJECT_ID(N'[dbo].[CVs]'))
ALTER TABLE [dbo].[CVs] DROP CONSTRAINT [FK_CVs_Students]
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_CVs_Purposes]') AND parent_object_id = OBJECT_ID(N'[dbo].[CVs]'))
ALTER TABLE [dbo].[CVs] DROP CONSTRAINT [FK_CVs_Purposes]
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_Companies_Users]') AND parent_object_id = OBJECT_ID(N'[dbo].[Companies]'))
ALTER TABLE [dbo].[Companies] DROP CONSTRAINT [FK_Companies_Users]
GO
/****** Object:  Table [dbo].[WorkExps]    Script Date: 06.11.2014 12:14:38 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[WorkExps]') AND type in (N'U'))
DROP TABLE [dbo].[WorkExps]
GO
/****** Object:  Table [dbo].[Users]    Script Date: 06.11.2014 12:14:38 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Users]') AND type in (N'U'))
DROP TABLE [dbo].[Users]
GO
/****** Object:  Table [dbo].[Students]    Script Date: 06.11.2014 12:14:38 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Students]') AND type in (N'U'))
DROP TABLE [dbo].[Students]
GO
/****** Object:  Table [dbo].[Sertificats]    Script Date: 06.11.2014 12:14:38 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Sertificats]') AND type in (N'U'))
DROP TABLE [dbo].[Sertificats]
GO
/****** Object:  Table [dbo].[Roles]    Script Date: 06.11.2014 12:14:38 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Roles]') AND type in (N'U'))
DROP TABLE [dbo].[Roles]
GO
/****** Object:  Table [dbo].[Purposes]    Script Date: 06.11.2014 12:14:38 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Purposes]') AND type in (N'U'))
DROP TABLE [dbo].[Purposes]
GO
/****** Object:  Table [dbo].[ProgramLanguagesCVs]    Script Date: 06.11.2014 12:14:38 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[ProgramLanguagesCVs]') AND type in (N'U'))
DROP TABLE [dbo].[ProgramLanguagesCVs]
GO
/****** Object:  Table [dbo].[ProgramLanguages]    Script Date: 06.11.2014 12:14:38 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[ProgramLanguages]') AND type in (N'U'))
DROP TABLE [dbo].[ProgramLanguages]
GO
/****** Object:  Table [dbo].[Pass]    Script Date: 06.11.2014 12:14:38 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Pass]') AND type in (N'U'))
DROP TABLE [dbo].[Pass]
GO
/****** Object:  Table [dbo].[LanguagesCVs]    Script Date: 06.11.2014 12:14:38 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[LanguagesCVs]') AND type in (N'U'))
DROP TABLE [dbo].[LanguagesCVs]
GO
/****** Object:  Table [dbo].[Languages]    Script Date: 06.11.2014 12:14:38 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Languages]') AND type in (N'U'))
DROP TABLE [dbo].[Languages]
GO
/****** Object:  Table [dbo].[Groups]    Script Date: 06.11.2014 12:14:38 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Groups]') AND type in (N'U'))
DROP TABLE [dbo].[Groups]
GO
/****** Object:  Table [dbo].[Faculties]    Script Date: 06.11.2014 12:14:38 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Faculties]') AND type in (N'U'))
DROP TABLE [dbo].[Faculties]
GO
/****** Object:  Table [dbo].[Educations]    Script Date: 06.11.2014 12:14:38 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Educations]') AND type in (N'U'))
DROP TABLE [dbo].[Educations]
GO
/****** Object:  Table [dbo].[CVs]    Script Date: 06.11.2014 12:14:38 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[CVs]') AND type in (N'U'))
DROP TABLE [dbo].[CVs]
GO
/****** Object:  Table [dbo].[Companies]    Script Date: 06.11.2014 12:14:38 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Companies]') AND type in (N'U'))
DROP TABLE [dbo].[Companies]
GO
USE [master]
GO
/****** Object:  Database [CVSystem]    Script Date: 06.11.2014 12:14:38 ******/
IF  EXISTS (SELECT name FROM sys.databases WHERE name = N'CVSystem')
DROP DATABASE [CVSystem]
GO
/****** Object:  Database [CVSystem]    Script Date: 06.11.2014 12:14:38 ******/
IF NOT EXISTS (SELECT name FROM sys.databases WHERE name = N'CVSystem')
BEGIN
CREATE DATABASE [CVSystem]
END

GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [CVSystem].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [CVSystem] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [CVSystem] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [CVSystem] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [CVSystem] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [CVSystem] SET ARITHABORT OFF 
GO
ALTER DATABASE [CVSystem] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [CVSystem] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [CVSystem] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [CVSystem] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [CVSystem] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [CVSystem] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [CVSystem] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [CVSystem] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [CVSystem] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [CVSystem] SET  ENABLE_BROKER 
GO
ALTER DATABASE [CVSystem] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [CVSystem] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [CVSystem] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [CVSystem] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [CVSystem] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [CVSystem] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [CVSystem] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [CVSystem] SET RECOVERY FULL 
GO
ALTER DATABASE [CVSystem] SET  MULTI_USER 
GO
ALTER DATABASE [CVSystem] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [CVSystem] SET DB_CHAINING OFF 
GO
ALTER DATABASE [CVSystem] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [CVSystem] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
EXEC sys.sp_db_vardecimal_storage_format N'CVSystem', N'ON'
GO
USE [CVSystem]
GO
/****** Object:  Table [dbo].[Companies]    Script Date: 06.11.2014 12:14:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Companies]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[Companies](
	[CompaniesId] [bigint] IDENTITY(1,1) NOT NULL,
	[Title] [nchar](10) NOT NULL,
	[Phone] [nchar](10) NOT NULL,
	[UsersId] [bigint] NOT NULL,
 CONSTRAINT [PK_Companies] PRIMARY KEY CLUSTERED 
(
	[CompaniesId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
END
GO
/****** Object:  Table [dbo].[CVs]    Script Date: 06.11.2014 12:14:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[CVs]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[CVs](
	[CVsId] [bigint] NOT NULL,
	[PurposesId] [bigint] NOT NULL,
	[Qualities] [nvarchar](max) NULL,
	[Others] [nvarchar](max) NULL,
	[DateStamp] [datetime] NOT NULL,
 CONSTRAINT [PK_CVs] PRIMARY KEY CLUSTERED 
(
	[CVsId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
END
GO
/****** Object:  Table [dbo].[Educations]    Script Date: 06.11.2014 12:14:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Educations]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[Educations](
	[EducationsId] [bigint] IDENTITY(1,1) NOT NULL,
	[StartYear] [int] NOT NULL,
	[EndYear] [int] NOT NULL,
	[NameOfInstitution] [nvarchar](max) NOT NULL,
	[Specialty] [nvarchar](max) NOT NULL,
	[Faculty] [nvarchar](max) NULL,
	[CVsId] [bigint] NOT NULL,
 CONSTRAINT [PK_Educations] PRIMARY KEY CLUSTERED 
(
	[EducationsId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
END
GO
/****** Object:  Table [dbo].[Faculties]    Script Date: 06.11.2014 12:14:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Faculties]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[Faculties](
	[FacultiesId] [bigint] IDENTITY(1,1) NOT NULL,
	[Title] [nvarchar](max) NULL,
 CONSTRAINT [PK_Faculties] PRIMARY KEY CLUSTERED 
(
	[FacultiesId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
END
GO
/****** Object:  Table [dbo].[Groups]    Script Date: 06.11.2014 12:14:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Groups]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[Groups](
	[GroupsId] [bigint] IDENTITY(1,1) NOT NULL,
	[FacultiesId] [bigint] NOT NULL,
	[Title] [nvarchar](max) NOT NULL,
 CONSTRAINT [PK_Groups] PRIMARY KEY CLUSTERED 
(
	[GroupsId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
END
GO
/****** Object:  Table [dbo].[Languages]    Script Date: 06.11.2014 12:14:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Languages]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[Languages](
	[LanguagesId] [bigint] IDENTITY(1,1) NOT NULL,
	[Title] [nvarchar](max) NOT NULL,
	[TitleEN] [nvarchar](max) NULL,
	[TitleUA] [nvarchar](max) NULL,
 CONSTRAINT [PK_Languages] PRIMARY KEY CLUSTERED 
(
	[LanguagesId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
END
GO
/****** Object:  Table [dbo].[LanguagesCVs]    Script Date: 06.11.2014 12:14:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[LanguagesCVs]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[LanguagesCVs](
	[LanguagesCVsId] [bigint] IDENTITY(1,1) NOT NULL,
	[LanguagesId] [bigint] NOT NULL,
	[CVsId] [bigint] NOT NULL,
	[Level] [int] NOT NULL,
 CONSTRAINT [PK_LanguagesCVs] PRIMARY KEY CLUSTERED 
(
	[LanguagesCVsId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
END
GO
/****** Object:  Table [dbo].[Pass]    Script Date: 06.11.2014 12:14:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Pass]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[Pass](
	[AccessPass] [nvarchar](max) NOT NULL
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
END
GO
/****** Object:  Table [dbo].[ProgramLanguages]    Script Date: 06.11.2014 12:14:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[ProgramLanguages]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[ProgramLanguages](
	[ProgramLanguagesId] [bigint] IDENTITY(1,1) NOT NULL,
	[Title] [nvarchar](max) NOT NULL,
 CONSTRAINT [PK_ProgramLanguages] PRIMARY KEY CLUSTERED 
(
	[ProgramLanguagesId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
END
GO
/****** Object:  Table [dbo].[ProgramLanguagesCVs]    Script Date: 06.11.2014 12:14:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[ProgramLanguagesCVs]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[ProgramLanguagesCVs](
	[ProgramLanguagesCVsId] [bigint] IDENTITY(1,1) NOT NULL,
	[ProgramLanguagesId] [bigint] NOT NULL,
	[CVsId] [bigint] NOT NULL,
 CONSTRAINT [PK_ProgramLanguagesCVs] PRIMARY KEY CLUSTERED 
(
	[ProgramLanguagesCVsId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
END
GO
/****** Object:  Table [dbo].[Purposes]    Script Date: 06.11.2014 12:14:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Purposes]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[Purposes](
	[PurposesId] [bigint] IDENTITY(1,1) NOT NULL,
	[Title] [nvarchar](max) NOT NULL,
	[TitleEN] [nvarchar](max) NULL,
	[TitleUA] [nvarchar](max) NULL,
 CONSTRAINT [PK_PurposesId] PRIMARY KEY CLUSTERED 
(
	[PurposesId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
END
GO
/****** Object:  Table [dbo].[Roles]    Script Date: 06.11.2014 12:14:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Roles]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[Roles](
	[Roles] [bigint] IDENTITY(1,1) NOT NULL,
 CONSTRAINT [PK_Roles] PRIMARY KEY CLUSTERED 
(
	[Roles] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
END
GO
/****** Object:  Table [dbo].[Sertificats]    Script Date: 06.11.2014 12:14:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Sertificats]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[Sertificats](
	[SertificatsId] [bigint] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](max) NOT NULL,
	[Year] [int] NOT NULL,
	[CVsId] [bigint] NOT NULL,
 CONSTRAINT [PK_Sertificats] PRIMARY KEY CLUSTERED 
(
	[SertificatsId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
END
GO
/****** Object:  Table [dbo].[Students]    Script Date: 06.11.2014 12:14:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Students]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[Students](
	[StudentsId] [bigint] IDENTITY(1,1) NOT NULL,
	[Surname] [nvarchar](max) NOT NULL,
	[Firstname] [nvarchar](max) NOT NULL,
	[Patronymic] [nvarchar](max) NULL,
	[Email] [nvarchar](max) NOT NULL,
	[Phone] [nvarchar](max) NOT NULL,
	[Birthday] [date] NOT NULL,
	[GroupsId] [bigint] NOT NULL,
	[Address] [nvarchar](max) NULL,
	[Skype] [nvarchar](50) NULL,
 CONSTRAINT [PK_Students] PRIMARY KEY CLUSTERED 
(
	[StudentsId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
END
GO
/****** Object:  Table [dbo].[Users]    Script Date: 06.11.2014 12:14:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Users]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[Users](
	[UsersId] [bigint] IDENTITY(1,1) NOT NULL,
	[Role] [int] NOT NULL,
	[login] [nvarchar](max) NOT NULL,
	[password] [nvarchar](max) NOT NULL,
 CONSTRAINT [PK_Users] PRIMARY KEY CLUSTERED 
(
	[UsersId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
END
GO
/****** Object:  Table [dbo].[WorkExps]    Script Date: 06.11.2014 12:14:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[WorkExps]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[WorkExps](
	[WorkExpsId] [bigint] IDENTITY(1,1) NOT NULL,
	[StartDate] [int] NOT NULL,
	[Duration] [int] NULL,
	[TypeDuration] [int] NULL,
	[NameOfInstitution] [nvarchar](max) NOT NULL,
	[Role] [nvarchar](max) NOT NULL,
	[CVsId] [bigint] NOT NULL,
	[IsNow] [tinyint] NULL,
 CONSTRAINT [PK_WorkExps] PRIMARY KEY CLUSTERED 
(
	[WorkExpsId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
END
GO
IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_Companies_Users]') AND parent_object_id = OBJECT_ID(N'[dbo].[Companies]'))
ALTER TABLE [dbo].[Companies]  WITH CHECK ADD  CONSTRAINT [FK_Companies_Users] FOREIGN KEY([UsersId])
REFERENCES [dbo].[Users] ([UsersId])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_Companies_Users]') AND parent_object_id = OBJECT_ID(N'[dbo].[Companies]'))
ALTER TABLE [dbo].[Companies] CHECK CONSTRAINT [FK_Companies_Users]
GO
IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_CVs_Purposes]') AND parent_object_id = OBJECT_ID(N'[dbo].[CVs]'))
ALTER TABLE [dbo].[CVs]  WITH CHECK ADD  CONSTRAINT [FK_CVs_Purposes] FOREIGN KEY([PurposesId])
REFERENCES [dbo].[Purposes] ([PurposesId])
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_CVs_Purposes]') AND parent_object_id = OBJECT_ID(N'[dbo].[CVs]'))
ALTER TABLE [dbo].[CVs] CHECK CONSTRAINT [FK_CVs_Purposes]
GO
IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_CVs_Students]') AND parent_object_id = OBJECT_ID(N'[dbo].[CVs]'))
ALTER TABLE [dbo].[CVs]  WITH CHECK ADD  CONSTRAINT [FK_CVs_Students] FOREIGN KEY([CVsId])
REFERENCES [dbo].[Students] ([StudentsId])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_CVs_Students]') AND parent_object_id = OBJECT_ID(N'[dbo].[CVs]'))
ALTER TABLE [dbo].[CVs] CHECK CONSTRAINT [FK_CVs_Students]
GO
IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_Educations_CVs]') AND parent_object_id = OBJECT_ID(N'[dbo].[Educations]'))
ALTER TABLE [dbo].[Educations]  WITH CHECK ADD  CONSTRAINT [FK_Educations_CVs] FOREIGN KEY([CVsId])
REFERENCES [dbo].[CVs] ([CVsId])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_Educations_CVs]') AND parent_object_id = OBJECT_ID(N'[dbo].[Educations]'))
ALTER TABLE [dbo].[Educations] CHECK CONSTRAINT [FK_Educations_CVs]
GO
IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_Groups_Faculties]') AND parent_object_id = OBJECT_ID(N'[dbo].[Groups]'))
ALTER TABLE [dbo].[Groups]  WITH CHECK ADD  CONSTRAINT [FK_Groups_Faculties] FOREIGN KEY([FacultiesId])
REFERENCES [dbo].[Faculties] ([FacultiesId])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_Groups_Faculties]') AND parent_object_id = OBJECT_ID(N'[dbo].[Groups]'))
ALTER TABLE [dbo].[Groups] CHECK CONSTRAINT [FK_Groups_Faculties]
GO
IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_LanguagesCVs_CVs]') AND parent_object_id = OBJECT_ID(N'[dbo].[LanguagesCVs]'))
ALTER TABLE [dbo].[LanguagesCVs]  WITH CHECK ADD  CONSTRAINT [FK_LanguagesCVs_CVs] FOREIGN KEY([CVsId])
REFERENCES [dbo].[CVs] ([CVsId])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_LanguagesCVs_CVs]') AND parent_object_id = OBJECT_ID(N'[dbo].[LanguagesCVs]'))
ALTER TABLE [dbo].[LanguagesCVs] CHECK CONSTRAINT [FK_LanguagesCVs_CVs]
GO
IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_LanguagesCVs_Languages]') AND parent_object_id = OBJECT_ID(N'[dbo].[LanguagesCVs]'))
ALTER TABLE [dbo].[LanguagesCVs]  WITH CHECK ADD  CONSTRAINT [FK_LanguagesCVs_Languages] FOREIGN KEY([LanguagesId])
REFERENCES [dbo].[Languages] ([LanguagesId])
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_LanguagesCVs_Languages]') AND parent_object_id = OBJECT_ID(N'[dbo].[LanguagesCVs]'))
ALTER TABLE [dbo].[LanguagesCVs] CHECK CONSTRAINT [FK_LanguagesCVs_Languages]
GO
IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_ProgramLanguagesCVs_CVs]') AND parent_object_id = OBJECT_ID(N'[dbo].[ProgramLanguagesCVs]'))
ALTER TABLE [dbo].[ProgramLanguagesCVs]  WITH CHECK ADD  CONSTRAINT [FK_ProgramLanguagesCVs_CVs] FOREIGN KEY([CVsId])
REFERENCES [dbo].[CVs] ([CVsId])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_ProgramLanguagesCVs_CVs]') AND parent_object_id = OBJECT_ID(N'[dbo].[ProgramLanguagesCVs]'))
ALTER TABLE [dbo].[ProgramLanguagesCVs] CHECK CONSTRAINT [FK_ProgramLanguagesCVs_CVs]
GO
IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_ProgramLanguagesCVs_ProgramLanguages]') AND parent_object_id = OBJECT_ID(N'[dbo].[ProgramLanguagesCVs]'))
ALTER TABLE [dbo].[ProgramLanguagesCVs]  WITH CHECK ADD  CONSTRAINT [FK_ProgramLanguagesCVs_ProgramLanguages] FOREIGN KEY([ProgramLanguagesId])
REFERENCES [dbo].[ProgramLanguages] ([ProgramLanguagesId])
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_ProgramLanguagesCVs_ProgramLanguages]') AND parent_object_id = OBJECT_ID(N'[dbo].[ProgramLanguagesCVs]'))
ALTER TABLE [dbo].[ProgramLanguagesCVs] CHECK CONSTRAINT [FK_ProgramLanguagesCVs_ProgramLanguages]
GO
IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_Sertificats_CVs]') AND parent_object_id = OBJECT_ID(N'[dbo].[Sertificats]'))
ALTER TABLE [dbo].[Sertificats]  WITH CHECK ADD  CONSTRAINT [FK_Sertificats_CVs] FOREIGN KEY([CVsId])
REFERENCES [dbo].[CVs] ([CVsId])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_Sertificats_CVs]') AND parent_object_id = OBJECT_ID(N'[dbo].[Sertificats]'))
ALTER TABLE [dbo].[Sertificats] CHECK CONSTRAINT [FK_Sertificats_CVs]
GO
IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_Students_Groups]') AND parent_object_id = OBJECT_ID(N'[dbo].[Students]'))
ALTER TABLE [dbo].[Students]  WITH CHECK ADD  CONSTRAINT [FK_Students_Groups] FOREIGN KEY([GroupsId])
REFERENCES [dbo].[Groups] ([GroupsId])
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_Students_Groups]') AND parent_object_id = OBJECT_ID(N'[dbo].[Students]'))
ALTER TABLE [dbo].[Students] CHECK CONSTRAINT [FK_Students_Groups]
GO
IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_WorkExps_CVs]') AND parent_object_id = OBJECT_ID(N'[dbo].[WorkExps]'))
ALTER TABLE [dbo].[WorkExps]  WITH CHECK ADD  CONSTRAINT [FK_WorkExps_CVs] FOREIGN KEY([CVsId])
REFERENCES [dbo].[CVs] ([CVsId])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_WorkExps_CVs]') AND parent_object_id = OBJECT_ID(N'[dbo].[WorkExps]'))
ALTER TABLE [dbo].[WorkExps] CHECK CONSTRAINT [FK_WorkExps_CVs]
GO
IF NOT EXISTS (SELECT * FROM sys.check_constraints WHERE object_id = OBJECT_ID(N'[dbo].[CK_Educations]') AND parent_object_id = OBJECT_ID(N'[dbo].[Educations]'))
ALTER TABLE [dbo].[Educations]  WITH CHECK ADD  CONSTRAINT [CK_Educations] CHECK  (([EndYear]>[StartYear]))
GO
IF  EXISTS (SELECT * FROM sys.check_constraints WHERE object_id = OBJECT_ID(N'[dbo].[CK_Educations]') AND parent_object_id = OBJECT_ID(N'[dbo].[Educations]'))
ALTER TABLE [dbo].[Educations] CHECK CONSTRAINT [CK_Educations]
GO
USE [master]
GO
ALTER DATABASE [CVSystem] SET  READ_WRITE 
GO