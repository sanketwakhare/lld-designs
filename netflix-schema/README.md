`postgres=# create user sanket with password 'sanket';`

CREATE ROLE

`postgres=# create database netflix__schema;`

CREATE DATABASE


`postgres=# grant all privileges on database netflix__schema to sanket;`

GRANT

---
### Class Diagram
![img.png](class_diagram.png)

---
### Schema Diagram

TODO

### Scratch Pad
Entities:
--------
User

Profile

Video

Actor

enums

ProfileType
KID
ADULT

videoStatus
COMPLETED
IN_PROGRESS

### Relationships:

User    Profile
1           M
1			1
[1 <-> M]

Profile  Video
1			M
M			1
[M <-> M]

Actor  Video
1			M
M			1
[M <-> M]

ProfileVideo Profile
M <-> 1

ProfileVideo Video
M <-> 1
-------------
### Entities:

User
email
password

Profile
name
type:enum
@ManyToOne
User user
@OneToMany(mappedBy = "profile")
List<ProfileVideo> profileVideos;

Video
title
desc
@ManyToMany
List<Actor> cast
@OneToMany(mappedBy = "video")
List<ProfileVideo> profileVideos

Actor
name
@ManyToMany(mappedBy = "cast")
List<Video> videos

ProfileVideo
@ManyToOne
Profile profile
@ManyToOne
Video video
video_status
lastTimeStamp

ActorVideo [ no need to create class as no attribute]
fees

-------
### schema

user [table]
email password

profile [table]
name
type
user_id

video [table]
title
desc

actor [table]
name

profile_video [mapping-table]
profile_id
video_id
video_status
lastTimeStamp

actor_video [mapping-table]
actor_id
video_id
---------------------------