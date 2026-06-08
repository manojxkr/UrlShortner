
# UrlShortner

A concise, self-hosted URL shortener built with Spring Boot. Provides per-user URL management, JWT authentication, and click analytics.

**Project overview**
- Shorten and manage URLs, track clicks and view analytics. Designed as a lightweight, extendable microservice.

**Features**
- Create short URLs (per-user)
- JWT-based authentication (register / login)
- Per-URL click events and date-range analytics
- Exposes a small REST API suitable for integration or frontend dashboards

**Tech stack**
- Java 21
- Spring Boot 4 (WebMVC, Data JPA, Security)
- PostgreSQL (JDBC)
- JWT via `jjwt` library
- Build: Maven (wrapper included)

## Installation
1. Configure a PostgreSQL database and create a database, e.g. `urlshortner`.
2. Copy `src/main/resources/application.properties` and set the datasource and JWT values:


## Usage
- Register a user, authenticate to receive a JWT, then include `Authorization: Bearer <token>` in requests that require authentication.

## API Endpoints

Authentication (public)
- `POST /api/auth/public/register` — register (JSON: `name`, `username`, `email`, `password`)
- `POST /api/auth/public/login` — login (JSON: `username`/`email`, `password`) — returns JWT

URL management (requires `ROLE_USER`, add header `Authorization: Bearer <token>`)
- `POST /api/urls/shorten` — shorten a URL (JSON body: `{"originalUrl":"https://..."}`)
- `GET /api/urls/myurls` — list authenticated user's URLs
- `GET /api/urls/analytics/{shortUrl}?startDate=<ISO_LOCAL_DATE_TIME>&endDate=<ISO_LOCAL_DATE_TIME>` — list click events for `shortUrl`
- `GET /api/urls/totalClicks?startDate=<YYYY-MM-DD>&endDate=<YYYY-MM-DD>` — total clicks grouped by date for the authenticated user

Redirect
- `GET /{shortUrl}` — redirect (HTTP 302) to original URL

## Future improvements
- Custom aliases and collision-free generation
- Rate limiting and abuse protection
- Frontend dashboard for analytics
- Link expiration and password-protected links
- Dockerfile and docker-compose for easy deployment
- Admin role and management APIs



