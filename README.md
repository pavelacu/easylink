# easylink-bk
Shortlink services backend implements in Java, Redis and Postgresql. Designed for deploy in Heroku Services.

## Test app

**Generate Shortlink**
- URL: /
- Method: POST
- MediaType: JSON
- Request: { url: "my_url"}
- Response: shortlink

**Redirect Shortlink to real URL**: parameter key is a shortlink generate with before method
- URL: /{key}
- Method: get
- MediaType: text
- Response: redirect to URL


## How Install

1. Get a Heroku account
2. Create a new application
3. Install add-ons
    - Heroku Postgres
    - Redis Enterprise Cloud

4. Set the enviroment variables in Heroku app:  (Heroku Dashboard > Settings > Config Vars > Reveal Config Vars)  
    Define your own environment variables:
    - JDBC_DATABASE_URL (Format: jdbc:postgresql://[host]:[port]/[database]?user=[myusername]&password=[mypass])
    - REDIS_URL (Format: redis://default:[password]@[host]:[port])

5. Install a builpacks: (Heroku Dashboard > Settings > Buildpacks > Add builpack)
    - heroku/java

6. Install Heroku cli (https://devcenter.heroku.com/articles/heroku-cli) and login.
```bash
heroku login
```

7. Enable Free Dynos (Not require paid, 500 hours free and sleep each 30 minutos without use). In this case is 'clock', but there are more options (web, worker and clock)
```bash
heroku ps:scale web=1 --app easylink
```

8. Deploy App (Heroku Dashboard > Deploy)
    - Syncronize your github account and deploy


