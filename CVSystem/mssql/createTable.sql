USE [CVSystem]
GO
ALTER TABLE [dbo].[WorkExps] DROP CONSTRAINT [FK_WorkExps_CVs]
GO
ALTER TABLE [dbo].[Students] DROP CONSTRAINT [FK_Students_Groups]
GO
ALTER TABLE [dbo].[Students] DROP CONSTRAINT [FK_Students_CVs]
GO
ALTER TABLE [dbo].[ProgramLanguagesCVs] DROP CONSTRAINT [FK_ProgramLanguagesCVs_ProgramLanguages]
GO
ALTER TABLE [dbo].[ProgramLanguagesCVs] DROP CONSTRAINT [FK_ProgramLanguagesCVs_CVs]
GO
ALTER TABLE [dbo].[LanguagesCVs] DROP CONSTRAINT [FK_LanguagesCVs_Languages]
GO
ALTER TABLE [dbo].[LanguagesCVs] DROP CONSTRAINT [FK_LanguagesCVs_CVs]
GO
ALTER TABLE [dbo].[Groups] DROP CONSTRAINT [FK_Groups_Faculties]
GO
ALTER TABLE [dbo].[Companies] DROP CONSTRAINT [FK_Companies_Users]
GO
/****** Object:  Table [dbo].[WorkExps]    Script Date: 25.09.2014 22:43:26 ******/
DROP TABLE [dbo].[WorkExps]
GO
/****** Object:  Table [dbo].[Users]    Script Date: 25.09.2014 22:43:27 ******/
DROP TABLE [dbo].[Users]
GO
/****** Object:  Table [dbo].[Students]    Script Date: 25.09.2014 22:43:27 ******/
DROP TABLE [dbo].[Students]
GO
/****** Object:  Table [dbo].[Roles]    Script Date: 25.09.2014 22:43:27 ******/
DROP TABLE [dbo].[Roles]
GO
/****** Object:  Table [dbo].[ProgramLanguagesCVs]    Script Date: 25.09.2014 22:43:27 ******/
DROP TABLE [dbo].[ProgramLanguagesCVs]
GO
/****** Object:  Table [dbo].[ProgramLanguages]    Script Date: 25.09.2014 22:43:27 ******/
DROP TABLE [dbo].[ProgramLanguages]
GO
/****** Object:  Table [dbo].[LanguagesCVs]    Script Date: 25.09.2014 22:43:27 ******/
DROP TABLE [dbo].[LanguagesCVs]
GO
/****** Object:  Table [dbo].[Languages]    Script Date: 25.09.2014 22:43:27 ******/
DROP TABLE [dbo].[Languages]
GO
/****** Object:  Table [dbo].[Groups]    Script Date: 25.09.2014 22:43:27 ******/
DROP TABLE [dbo].[Groups]
GO
/****** Object:  Table [dbo].[Faculties]    Script Date: 25.09.2014 22:43:27 ******/
DROP TABLE [dbo].[Faculties]
GO
/****** Object:  Table [dbo].[CVs]    Script Date: 25.09.2014 22:43:27 ******/
DROP TABLE [dbo].[CVs]
GO
/****** Object:  Table [dbo].[Companies]    Script Date: 25.09.2014 22:43:27 ******/
DROP TABLE [dbo].[Companies]
GO
USE [master]
GO
/****** Object:  Database [CVSystem]    Script Date: 25.09.2014 22:43:27 ******/
DROP DATABASE [CVSystem]
GO
/****** Object:  Database [CVSystem]    Script Date: 25.09.2014 22:43:27 ******/
CREATE DATABASE [CVSystem]
GO
ALTER DATABASE [CVSystem] SET COMPATIBILITY_LEVEL = 120
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
ALTER DATABASE [CVSystem] SET DELAYED_DURABILITY = DISABLED 
GO
USE [CVSystem]
GO
/****** Object:  Table [dbo].[Companies]    Script Date: 25.09.2014 22:43:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
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

GO
/****** Object:  Table [dbo].[CVs]    Script Date: 25.09.2014 22:43:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CVs](
	[CVsId] [bigint] IDENTITY(1,1) NOT NULL,
 CONSTRAINT [PK_CVs] PRIMARY KEY CLUSTERED 
(
	[CVsId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Faculties]    Script Date: 25.09.2014 22:43:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Faculties](
	[FacultiesId] [bigint] IDENTITY(1,1) NOT NULL,
	[Title] [nvarchar](max) NULL,
 CONSTRAINT [PK_Faculties] PRIMARY KEY CLUSTERED 
(
	[FacultiesId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Groups]    Script Date: 25.09.2014 22:43:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Groups](
	[GroupsId] [bigint] IDENTITY(1,1) NOT NULL,
	[FacultiesId] [bigint] NOT NULL,
	[Title] [nvarchar](max) NOT NULL,
 CONSTRAINT [PK_Groups] PRIMARY KEY CLUSTERED 
(
	[GroupsId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Languages]    Script Date: 25.09.2014 22:43:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Languages](
	[LanguagesId] [bigint] IDENTITY(1,1) NOT NULL,
	[Title] [nvarchar](max) NOT NULL,
 CONSTRAINT [PK_Languages] PRIMARY KEY CLUSTERED 
(
	[LanguagesId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[LanguagesCVs]    Script Date: 25.09.2014 22:43:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LanguagesCVs](
	[LanguagesCVsId] [bigint] IDENTITY(1,1) NOT NULL,
	[LanguagesId] [bigint] NOT NULL,
	[CVsId] [bigint] NOT NULL,
 CONSTRAINT [PK_LanguagesCVs] PRIMARY KEY CLUSTERED 
(
	[LanguagesCVsId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[ProgramLanguages]    Script Date: 25.09.2014 22:43:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ProgramLanguages](
	[ProgramLanguagesId] [bigint] IDENTITY(1,1) NOT NULL,
	[Title] [nvarchar](max) NOT NULL,
 CONSTRAINT [PK_ProgramLanguages] PRIMARY KEY CLUSTERED 
(
	[ProgramLanguagesId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[ProgramLanguagesCVs]    Script Date: 25.09.2014 22:43:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ProgramLanguagesCVs](
	[ProgramLanguagesCVsId] [bigint] IDENTITY(1,1) NOT NULL,
	[ProgramLanguagesId] [bigint] NOT NULL,
	[CVsId] [bigint] NOT NULL,
 CONSTRAINT [PK_ProgramLanguagesCVs] PRIMARY KEY CLUSTERED 
(
	[ProgramLanguagesCVsId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Roles]    Script Date: 25.09.2014 22:43:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Roles](
	[Roles] [bigint] IDENTITY(1,1) NOT NULL,
 CONSTRAINT [PK_Roles] PRIMARY KEY CLUSTERED 
(
	[Roles] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Students]    Script Date: 25.09.2014 22:43:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Students](
	[StudentsId] [bigint] IDENTITY(1,1) NOT NULL,
	[Surname] [nvarchar](max) NOT NULL,
	[Firstname] [nvarchar](max) NOT NULL,
	[Lastname] [nvarchar](max) NOT NULL,
	[GroupsId] [bigint] NOT NULL,
	[CVsId] [bigint] NOT NULL,
 CONSTRAINT [PK_Students] PRIMARY KEY CLUSTERED 
(
	[StudentsId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Users]    Script Date: 25.09.2014 22:43:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
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

GO
/****** Object:  Table [dbo].[WorkExps]    Script Date: 25.09.2014 22:43:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[WorkExps](
	[WorkExpsId] [bigint] NOT NULL,
	[StartDate] [int] NOT NULL,
	[Duration] [int] NOT NULL,
	[TypeDuration] [int] NOT NULL,
	[NameOfInstitution] [nvarchar](max) NOT NULL,
	[Role] [nvarchar](max) NOT NULL,
	[CVsId] [bigint] NOT NULL,
 CONSTRAINT [PK_WorkExps] PRIMARY KEY CLUSTERED 
(
	[WorkExpsId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
ALTER TABLE [dbo].[Companies]  WITH CHECK ADD  CONSTRAINT [FK_Companies_Users] FOREIGN KEY([UsersId])
REFERENCES [dbo].[Users] ([UsersId])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Companies] CHECK CONSTRAINT [FK_Companies_Users]
GO
ALTER TABLE [dbo].[Groups]  WITH CHECK ADD  CONSTRAINT [FK_Groups_Faculties] FOREIGN KEY([FacultiesId])
REFERENCES [dbo].[Faculties] ([FacultiesId])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Groups] CHECK CONSTRAINT [FK_Groups_Faculties]
GO
ALTER TABLE [dbo].[LanguagesCVs]  WITH CHECK ADD  CONSTRAINT [FK_LanguagesCVs_CVs] FOREIGN KEY([CVsId])
REFERENCES [dbo].[CVs] ([CVsId])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[LanguagesCVs] CHECK CONSTRAINT [FK_LanguagesCVs_CVs]
GO
ALTER TABLE [dbo].[LanguagesCVs]  WITH CHECK ADD  CONSTRAINT [FK_LanguagesCVs_Languages] FOREIGN KEY([LanguagesId])
REFERENCES [dbo].[Languages] ([LanguagesId])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[LanguagesCVs] CHECK CONSTRAINT [FK_LanguagesCVs_Languages]
GO
ALTER TABLE [dbo].[ProgramLanguagesCVs]  WITH CHECK ADD  CONSTRAINT [FK_ProgramLanguagesCVs_CVs] FOREIGN KEY([CVsId])
REFERENCES [dbo].[CVs] ([CVsId])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[ProgramLanguagesCVs] CHECK CONSTRAINT [FK_ProgramLanguagesCVs_CVs]
GO
ALTER TABLE [dbo].[ProgramLanguagesCVs]  WITH CHECK ADD  CONSTRAINT [FK_ProgramLanguagesCVs_ProgramLanguages] FOREIGN KEY([ProgramLanguagesId])
REFERENCES [dbo].[ProgramLanguages] ([ProgramLanguagesId])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[ProgramLanguagesCVs] CHECK CONSTRAINT [FK_ProgramLanguagesCVs_ProgramLanguages]
GO
ALTER TABLE [dbo].[Students]  WITH CHECK ADD  CONSTRAINT [FK_Students_CVs] FOREIGN KEY([CVsId])
REFERENCES [dbo].[CVs] ([CVsId])
GO
ALTER TABLE [dbo].[Students] CHECK CONSTRAINT [FK_Students_CVs]
GO
ALTER TABLE [dbo].[Students]  WITH CHECK ADD  CONSTRAINT [FK_Students_Groups] FOREIGN KEY([GroupsId])
REFERENCES [dbo].[Groups] ([GroupsId])
GO
ALTER TABLE [dbo].[Students] CHECK CONSTRAINT [FK_Students_Groups]
GO
ALTER TABLE [dbo].[WorkExps]  WITH CHECK ADD  CONSTRAINT [FK_WorkExps_CVs] FOREIGN KEY([CVsId])
REFERENCES [dbo].[CVs] ([CVsId])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[WorkExps] CHECK CONSTRAINT [FK_WorkExps_CVs]
GO
USE [master]
GO
ALTER DATABASE [CVSystem] SET  READ_WRITE 
GO
