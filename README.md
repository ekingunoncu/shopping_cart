# ShoppingCart Spring App

### ShoppingCart is a web application which is created for Shopping processes.

* Create and delete Cart, Products, Categories.
* Create Campaigns and Coupons with RATE and AMOUNT discount types.
* Add Products to Cart
* Get detailed Cart information after adding products to Cart.
* You can apply Coupon at getting information process. Campaigns on different Categories will be automatically processed.
* Get detailed Cart information returns Delivery cost too.


### Usage 

You can get http request templates by using postman link below.

[https://www.getpostman.com/collections/04117159ebcff1b56d91](https://www.getpostman.com/collections/04117159ebcff1b56d91)

[Screenshots of usage (postman request) ](screenshots/README.md)

#### Example response 

This is the response which returns desired cart's information with details.

![sample response](https://raw.githubusercontent.com/ekingunoncu/shopping_cart/master/screenshots/get_carts_information.png)

### Installation

This project uses postgresql as database.

I preferred to use database as docker container. \
PgAdmin container is also optional for simple usage. 

Use docker-compose file under docker-compose/docker-compose.yml to create postgres and pgadmin container: 

```bash
version: '3.5'
services:
  postgres:
    container_name: ${POSTGRES_CONTAINER_NAME}
    image: postgres
    environment:
      POSTGRES_USER: ${POSTGRES_USER}
      POSTGRES_PASSWORD: ${POSTGRES_PASSWORD}
      PGDATA: /data/postgres
    volumes:
       - postgres:/data/postgres
    ports:
      - "5432:5432"
    networks:
      - postgres
    restart: unless-stopped

  pgadmin:
    container_name: pgadmin_container
    image: dpage/pgadmin4
    environment:
      PGADMIN_DEFAULT_EMAIL: ${PGADMIN_DEFAULT_EMAIL}
      PGADMIN_DEFAULT_PASSWORD: ${PGADMIN_DEFAULT_PASSWORD}
    volumes:
       - pgadmin:/root/.pgadmin
    ports:
      - "${PGADMIN_PORT}:80"
    networks:
      - postgres
    restart: unless-stopped

networks:
  postgres:
    driver: bridge

volumes:
    postgres:
    pgadmin:
```

You can change environment variables using .env file under docker-compose directory.

```bash
POSTGRES_CONTAINER_NAME=shopping-cart_postgres
POSTGRES_USER=postgres
POSTGRES_PASSWORD=Ab123123
PGADMIN_DEFAULT_EMAIL=example@gmail.com
PGADMIN_DEFAULT_PASSWORD=Ab123123
PGADMIN_PORT=5000
SHOPPING_PORT=4000
```

After executing ``` docker-compose up ``` command under docker-compose directory, we will have two running containers hopefully. 

### Db

Under db directory there are sql queries. The file with .sql extension is has the manual queries executed by me at development processes.

The file with .out extension is a dump file and it is really simple to use. It will generate all db users tables and some data for our webapp.

execute this command under db directory :

```sudo docker exec -t -u postgres ${POSTGRES_CONTAINER_NAME} sql -f shopping_cart.out  ```


#### Access to PgAdmin: 
* **URL:** `http://localhost:5050`
* **Username:** example@gmail.com (as a default)
* **Password:** Ab123123 (as a default)

#### Add a new server in PgAdmin:
* **Host name/address** `postgres`
* **Port** `5432`
* **Username** as `POSTGRES_USER`, by default: `postgres`
* **Password** as `POSTGRES_PASSWORD`, by default `Ab123123`
