# LMS Frontend (React + Vite, JS)

A minimal frontend targeting the LMS backend (Spring Cloud microservices) with API Gateway at `http://localhost:8080`.

## Quick start
```bash
npm i
npm run dev
```

Set a custom API base if needed:
```bash
VITE_API_BASE=http://localhost:8080 npm run dev
```

## Pages & Endpoints
- Auth: `POST /auth/login`, `POST /auth/register`
- Catalogue: `GET /catalogues`, `GET /catalogues/{id}`, `PUT /catalogues/{id}/rating`
- Members: `GET /members/{id}`
- Circulations: `GET /circulations/exists?catalogueId=&memberId=`, `PUT /circulations/{id}/return`
- Reviews: `GET /reviews`, `GET /reviews/catalogue/{catalogue_id}`
- Acquisitions (protected): `GET /acquisitions`

JWT (if returned by login) is stored in `localStorage` as `jwt` and sent in `Authorization: Bearer <token>`.
