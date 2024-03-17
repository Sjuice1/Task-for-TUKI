create table correnet_task_schema.image(
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    image_url varchar(1024) NOT NULL
);

create table correnet_task_schema.tag(
  id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY ,
  name VARCHAR(512) NOT NULL,
  image_id BIGINT references correnet_task_schema.image(id) NOT NULL
);