// src/api.js
const API_BASE = "http://localhost:8080";

// ------------------- Acquisition -------------------
export async function getAcquisitions() {
  const res = await fetch(`${API_BASE}/acquisitions`);
  return res.json();
}

export async function getAcquisition(id) {
  const res = await fetch(`${API_BASE}/acquisitions/${id}`);
  return res.json();
}

export async function addAcquisition(data) {
  const res = await fetch(`${API_BASE}/acquisitions/create`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({
      title: data.title,
      author: data.author,
      supplier: data.supplier,
      status: data.status,
      description: data.description,
    }),
  });

  if (!res.ok) {
    throw new Error(`Failed to add acquisition: ${res.status}`);
  }

  return res.json();
}


export async function updateAcquisition(id, data) {
  const res = await fetch(`${API_BASE}/acquisitions/${id}`, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data),
  });
  return res.json();
}

export async function deleteAcquisition(id) {
  await fetch(`${API_BASE}/acquisitions/${id}`, { method: "DELETE" });
}

// ------------------- Catalogue -------------------
export async function getCatalogues() {
  const res = await fetch(`${API_BASE}/catalogues`);
  return res.json();
}

export async function getCatalogue(id) {
  const res = await fetch(`${API_BASE}/catalogues/${id}`);
  return res.json();
}

export async function addCatalogue(data) {
  const res = await fetch(`${API_BASE}/catalogues/add`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data),
  });
  return res.json();
}


export async function updateCatalogue(id, data) {
  const res = await fetch(`${API_BASE}/catalogues/${id}`, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data),
  });
  return res.json();
}

export async function updateCatalogueRating(id, rating) {
  const res = await fetch(`${API_BASE}/catalogues/${id}/rating`, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ rating }),
  });
  return res.json();
}

export async function deleteCatalogue(id) {
  await fetch(`${API_BASE}/catalogues/${id}`, { method: "DELETE" });
}

// ------------------- Members -------------------
export async function getMembers() {
  const res = await fetch(`${API_BASE}/members`);
  return res.json();
}

export async function getMember(id) {
  const res = await fetch(`${API_BASE}/members/${id}`);
  return res.json();
}

export const addMember = async (member) => {
  const res = await fetch("http://localhost:8080/members/add", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(member),
  });
  return res.json();
};


export async function updateMember(id, data) {
  const res = await fetch(`${API_BASE}/members/${id}`, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data),
  });
  return res.json();
}

export async function deleteMember(id) {
  await fetch(`${API_BASE}/members/${id}`, { method: "DELETE" });
}

// ------------------- Circulation -------------------
export async function getCirculations() {
  const res = await fetch(`${API_BASE}/circulations`);
  return res.json();
}

export async function getCirculation(id) {
  const res = await fetch(`${API_BASE}/circulations/${id}`);
  return res.json();
}

export async function addCirculation(data) {
  const res = await fetch(`${API_BASE}/circulations/create`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({
      member_id: data.memberId,
      catalogue_id: data.catalogueId,
    }),
  });
  return res.json();
}


export async function returnCirculation(id, data) {
  const res = await fetch(`${API_BASE}/circulations/${id}/return`, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data),
  });
  return res.json();
}

export async function deleteCirculation(id) {
  await fetch(`${API_BASE}/circulations/${id}`, { method: "DELETE" });
}

export async function checkCirculationExists(memberId, catalogueId) {
  const res = await fetch(
    `${API_BASE}/circulations/exists?memberId=${memberId}&catalogueId=${catalogueId}`
  );
  return res.json();
}

// ------------------- Reviews -------------------
export async function getReviews() {
  const res = await fetch(`${API_BASE}/reviews`);
  return res.json();
}

export async function getReview(id) {
  const res = await fetch(`${API_BASE}/reviews/${id}`);
  return res.json();
}

export async function getReviewsByCatalogue(catalogueId) {
  const res = await fetch(`${API_BASE}/reviews/catalogue/${catalogueId}`);
  return res.json();
}

export async function getReviewsByMember(memberId) {
  const res = await fetch(`${API_BASE}/reviews/member/${memberId}`);
  return res.json();
}

export async function addReview(data) {
  const res = await fetch(`${API_BASE}/reviews/add`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({
      member_id: data.member_id,
      catalogue_id: data.catalogue_id,
      comment: data.comment,
      rating: parseInt(data.rating, 10), // ensure it's sent as integer
    }),
  });

  if (!res.ok) {
    throw new Error(`Failed to add review: ${res.statusText}`);
  }

  return res.json();
}


export async function updateReview(id, data) {
  const res = await fetch(`${API_BASE}/reviews/${id}`, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data),
  });
  return res.json();
}

export async function deleteReview(id) {
  await fetch(`${API_BASE}/reviews/${id}`, { method: "DELETE" });
}

// ------------------- Auth (Login/Register) -------------------
// src/services/api.js
export async function login(email, password) {
  const res = await fetch(`${API_BASE}/auth/login`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ email, password }), // ✅ correct shape
  });

  if (!res.ok) {
    throw new Error("Login failed");
  }

  return res.json();
}



export async function register(data) {
  const res = await fetch(`${API_BASE}/auth/register`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data),
  });
  return res.json();
}
