# Design Netflix Schema
Design Class Diagram and Database Schema for a system like Netflix with the following Use Cases.

## Use Case

1. Netflix has users
2. Every user has an email and a password
3. Users can create profiles to have separate independent environments.
4. Each profile has a name and a type. Type can be KID or ADULT.
5. There are multiple videos on Netflix.
6. For each video, there will be a title, description and a cast.
7. A cast is a list of actors who were a part of the video. For each actor, we need to know their name and list of videos they were a part of.
8. For every video, for any profile who watched that video, we need to know the status (COMPLETED/ IN PROGRESS).
9. For every profile for whom a video is in progress, we want to know their last watch timestamp.
---
### Database Setup

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

---
### Scratch Pad

### Entities

1. User 
2. Profile 
3. Video 
4. Actor

### Enums

1. ProfileType 
- KID
- ADULT

2. VideoStatusType
- COMPLETED
- IN_PROGRESS

### Relationships

User    Profile
- 1   ->      M
- 1   <-      1
- [1 <-> M]

Profile  Video
- 1   ->      M
- M   <-      1
- [M <-> M]

Actor  Video
- 1   ->      M
- M   <-      1
- [M <-> M]

ProfileVideo  Profile
- M   <->         1

ProfileVideo  Video
- M   <->         1
-------------
### Entity Classes

##### User
- email
- password

##### Profile
- name
- type:enum
- @ManyToOne 
  - User user
- @OneToMany(mappedBy = "profile")
  - List<ProfileVideo> profileVideos;

##### Video
- title
- desc
- @ManyToMany
  - List<Actor> cast
- @OneToMany(mappedBy = "video")
  - List<ProfileVideo> profileVideos

##### Actor
- name
- @ManyToMany(mappedBy = "cast")
  - List<Video> videos

##### ProfileVideo
- @ManyToOne
  - Profile profile
- @ManyToOne
  - Video video
- video_status
- lastTimeStamp

##### ActorVideo [ no need to create class as no attribute]

-------
### Database Schema

##### user [table]

| email | password |

##### profile [table]
| name | type | user_id |

##### video [table]
| title | desc |

##### actor [table]
name

##### profile_video - [mapping-table]
profile_id | video_id | video_status | lastTimeStamp

##### actor_video - [mapping-table]
actor_id | video_id

---