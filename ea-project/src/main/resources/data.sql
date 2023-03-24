SET IDENTITY_INSERT role_table ON
INSERT INTO  role_table(id,role) values (1,'STUDENT')
INSERT INTO  role_table(id,role) values (2,'FACULTY')
INSERT INTO  role_table(id,role) values (3,'STAFF')
INSERT INTO  role_table(id,role) values (4,'ADMIN')
SET IDENTITY_INSERT role_table OFF

SET IDENTITY_INSERT member_table ON
    INSERT INTO member_table(id,email,firstName,lastName,password,role_id) values (1,'Abenezer@miu.edu','Abenezer','Dana','$2a$08$kOieqCi3cs2UtCdPUjhhRO3I9HI8XecWZxEG1TRgSAcTacA7JR86e',1)
SET IDENTITY_INSERT member_table OFF

SET IDENTITY_INSERT badge_table ON
    INSERT INTO badge_table(id,status,memberId) values (1,0,1)
SET IDENTITY_INSERT badge_table OFF


SET IDENTITY_INSERT plan_table ON
INSERT INTO plan_table(id,description,name) values (1,'test descripton','Plan Dining Plan')
SET IDENTITY_INSERT plan_table OFF



SET IDENTITY_INSERT membership_table ON
INSERT INTO membership_table(id,endDate,membershipType,numberOfAllowances,currentUsageCount, startDate,plan_id, resetTime,memberId) values (1,'2024-12-31',0,20,0,'2023-03-01',1, 'WEEKLY',1)
SET IDENTITY_INSERT membership_table OFF

SET IDENTITY_INSERT membership_table ON
INSERT INTO membership_table(id,endDate,membershipType,numberOfAllowances,currentUsageCount, startDate,plan_id, resetTime,memberId) values (2,'2024-12-31',2,20,0,'2023-03-01',1, 'WEEKLY',1)
SET IDENTITY_INSERT membership_table OFF


SET IDENTITY_INSERT location_table ON
INSERT INTO location_table(id,capacity,description,locationType,name,planId) values (1,200,'Dining hall location description','DINING_HALL','Dining',1)
SET IDENTITY_INSERT location_table OFF



SET IDENTITY_INSERT timeslot_table ON
INSERT INTO timeslot_table(id,dayOfWeek,endTime,startTime) values (1,'SUNDAY','14:00','11:00')
INSERT INTO timeslot_table(id,dayOfWeek,endTime,startTime) values (2,'SUNDAY','20:00','18:00')
INSERT INTO timeslot_table(id,dayOfWeek,endTime,startTime) values (3,'MONDAY','10:00','08:30')
INSERT INTO timeslot_table(id,dayOfWeek,endTime,startTime) values (4,'MONDAY','14:00','12:00')
INSERT INTO timeslot_table(id,dayOfWeek,endTime,startTime) values (5,'MONDAY','20:00','6:00')
INSERT INTO timeslot_table(id,dayOfWeek,endTime,startTime) values (6,'TUESDAY','10:00','08:30')
INSERT INTO timeslot_table(id,dayOfWeek,endTime,startTime) values (7,'TUESDAY','14:00','12:00')
INSERT INTO timeslot_table(id,dayOfWeek,endTime,startTime) values (8,'TUESDAY','20:00','6:00')
INSERT INTO timeslot_table(id,dayOfWeek,endTime,startTime) values (9,'WEDNESDAY','10:00','08:30')
INSERT INTO timeslot_table(id,dayOfWeek,endTime,startTime) values (10,'WEDNESDAY','14:00','12:00')
INSERT INTO timeslot_table(id,dayOfWeek,endTime,startTime) values (11,'WEDNESDAY','20:00','6:00')
INSERT INTO timeslot_table(id,dayOfWeek,endTime,startTime) values (12,'THURSDAY','10:00','08:30')
INSERT INTO timeslot_table(id,dayOfWeek,endTime,startTime) values (13,'THURSDAY','14:00','12:00')
INSERT INTO timeslot_table(id,dayOfWeek,endTime,startTime) values (14,'THURSDAY','20:00','6:00')
INSERT INTO timeslot_table(id,dayOfWeek,endTime,startTime) values (15,'THURSDAY','10:00','08:30')
INSERT INTO timeslot_table(id,dayOfWeek,endTime,startTime) values (16,'THURSDAY','14:00','12:00')
INSERT INTO timeslot_table(id,dayOfWeek,endTime,startTime) values (17,'THURSDAY','20:00','6:00')
INSERT INTO timeslot_table(id,dayOfWeek,endTime,startTime) values (18,'FRIDAY','10:00','08:30')
INSERT INTO timeslot_table(id,dayOfWeek,endTime,startTime) values (19,'FRIDAY','14:00','12:00')
INSERT INTO timeslot_table(id,dayOfWeek,endTime,startTime) values (20,'FRIDAY','20:00','6:00')

SET IDENTITY_INSERT timeslot_table OFF

INSERT INTO location_timeslot_table(location_table_id,timeSlots_id) values (1,1)
INSERT INTO location_timeslot_table(location_table_id,timeSlots_id) values (1,2)
INSERT INTO location_timeslot_table(location_table_id,timeSlots_id) values (1,3)
INSERT INTO location_timeslot_table(location_table_id,timeSlots_id) values (1,4)
INSERT INTO location_timeslot_table(location_table_id,timeSlots_id) values (1,5)
INSERT INTO location_timeslot_table(location_table_id,timeSlots_id) values (1,17)


-- SET IDENTITY_INSERT transaction_table ON
-- INSERT INTO transaction_table(id,transactionDateTime,transactionType,badgeId,locationId,membership_id,plan_id) values (1,200,'Dining hall location description','DINING_HALL','Dining',1)
-- SET IDENTITY_INSERT location_table OFF