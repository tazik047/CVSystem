USE [CVSystem]
GO

SET IDENTITY_INSERT [dbo].[Purposes] ON 

GO
INSERT [dbo].[Purposes] ([PurposesId], [Title]) VALUES (1, N'Java Junior')
GO
INSERT [dbo].[Purposes] ([PurposesId], [Title]) VALUES (2, N'C# Junior')
GO
INSERT [dbo].[Purposes] ([PurposesId], [Title]) VALUES (3, N'C# Middle')
GO
INSERT [dbo].[Purposes] ([PurposesId], [Title]) VALUES (4, N'C# Senior')
GO
INSERT [dbo].[Purposes] ([PurposesId], [Title]) VALUES (5, N'Senior Java developer')
GO
INSERT [dbo].[Purposes] ([PurposesId], [Title]) VALUES (6, N'Senior .Net developer co знанием frontend-технологий')
GO
INSERT [dbo].[Purposes] ([PurposesId], [Title]) VALUES (7, N'Middle .Net / SharePoint developer')
GO
INSERT [dbo].[Purposes] ([PurposesId], [Title]) VALUES (8, N'PHP/CMS developer')
GO
INSERT [dbo].[Purposes] ([PurposesId], [Title]) VALUES (9, N'Requirements Analyst/Sales')
GO
SET IDENTITY_INSERT [dbo].[Purposes] OFF
GO
SET IDENTITY_INSERT [dbo].[Languages] ON 

GO
INSERT [dbo].[Languages] ([LanguagesId], [Title]) VALUES (1, N'Русский')
GO
INSERT [dbo].[Languages] ([LanguagesId], [Title]) VALUES (2, N'Украинский')
GO
INSERT [dbo].[Languages] ([LanguagesId], [Title]) VALUES (3, N'Английский')
GO
INSERT [dbo].[Languages] ([LanguagesId], [Title]) VALUES (4, N'Французский')
GO
INSERT [dbo].[Languages] ([LanguagesId], [Title]) VALUES (5, N'Немецкий')
GO
INSERT [dbo].[Languages] ([LanguagesId], [Title]) VALUES (6, N'Китайский')
GO
SET IDENTITY_INSERT [dbo].[Languages] OFF
GO
SET IDENTITY_INSERT [dbo].[ProgramLanguages] ON 

GO
INSERT [dbo].[ProgramLanguages] ([ProgramLanguagesId], [Title]) VALUES (1, N'Haskell')
GO
INSERT [dbo].[ProgramLanguages] ([ProgramLanguagesId], [Title]) VALUES (2, N'Java')
GO
INSERT [dbo].[ProgramLanguages] ([ProgramLanguagesId], [Title]) VALUES (3, N'C#')
GO
INSERT [dbo].[ProgramLanguages] ([ProgramLanguagesId], [Title]) VALUES (4, N'Paskal')
GO
INSERT [dbo].[ProgramLanguages] ([ProgramLanguagesId], [Title]) VALUES (5, N'Visual C++')
GO
INSERT [dbo].[ProgramLanguages] ([ProgramLanguagesId], [Title]) VALUES (6, N'Visual Basic')
GO
INSERT [dbo].[ProgramLanguages] ([ProgramLanguagesId], [Title]) VALUES (7, N'Assembler')
GO
INSERT [dbo].[ProgramLanguages] ([ProgramLanguagesId], [Title]) VALUES (8, N'C')
GO
INSERT [dbo].[ProgramLanguages] ([ProgramLanguagesId], [Title]) VALUES (9, N'Turbo Paskal')
GO
INSERT [dbo].[ProgramLanguages] ([ProgramLanguagesId], [Title]) VALUES (10, N'HTML')
GO
INSERT [dbo].[ProgramLanguages] ([ProgramLanguagesId], [Title]) VALUES (11, N'CSS')
GO
INSERT [dbo].[ProgramLanguages] ([ProgramLanguagesId], [Title]) VALUES (12, N'PHP')
GO
INSERT [dbo].[ProgramLanguages] ([ProgramLanguagesId], [Title]) VALUES (13, N'JavaScript')
GO
SET IDENTITY_INSERT [dbo].[ProgramLanguages] OFF
GO

SET IDENTITY_INSERT [dbo].[Faculties] ON 

GO
INSERT [dbo].[Faculties] ([FacultiesId], [Title]) VALUES (4, N'ПММ')
GO
INSERT [dbo].[Faculties] ([FacultiesId], [Title]) VALUES (5, N'ФНІГ')
GO
SET IDENTITY_INSERT [dbo].[Faculties] OFF
GO
SET IDENTITY_INSERT [dbo].[Groups] ON 

GO
INSERT [dbo].[Groups] ([GroupsId], [FacultiesId], [Title]) VALUES (1, 4, N'ЕК-11-1')
GO
INSERT [dbo].[Groups] ([GroupsId], [FacultiesId], [Title]) VALUES (2, 4, N'ІНФу-12-1')
GO
INSERT [dbo].[Groups] ([GroupsId], [FacultiesId], [Title]) VALUES (3, 4, N'ІНФ-11-1')
GO
INSERT [dbo].[Groups] ([GroupsId], [FacultiesId], [Title]) VALUES (4, 4, N'ІНФ-11-2')
GO
INSERT [dbo].[Groups] ([GroupsId], [FacultiesId], [Title]) VALUES (5, 4, N'ПМу-12-1')
GO
INSERT [dbo].[Groups] ([GroupsId], [FacultiesId], [Title]) VALUES (6, 4, N'ПМ-11-1')
GO
INSERT [dbo].[Groups] ([GroupsId], [FacultiesId], [Title]) VALUES (7, 4, N'САу-12-1')
GO
INSERT [dbo].[Groups] ([GroupsId], [FacultiesId], [Title]) VALUES (8, 4, N'СА-11-1')
GO
INSERT [dbo].[Groups] ([GroupsId], [FacultiesId], [Title]) VALUES (9, 5, N'ЕКи-11-2')
GO
INSERT [dbo].[Groups] ([GroupsId], [FacultiesId], [Title]) VALUES (10, 5, N'ЕКи-11-3')
GO
SET IDENTITY_INSERT [dbo].[Groups] OFF
GO

INSERT [dbo].[Pass] ([AccessPass]) VALUES (N',b,bpzrb')
GO