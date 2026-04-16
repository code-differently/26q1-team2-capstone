import { useEffect, useState } from "react";
import { getResources } from "../api/api";

export default function ResourceAlertCard() {
  const [resourceData, setResourceData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    async function loadResources() {
      try {
        setLoading(true);
        setError("");

        const data = await getResources();
        setResourceData(data);
      } catch (err) {
        console.error("Failed to load resources:", err);
        setError("Failed to load support resources.");
      } finally {
        setLoading(false);
      }
    }

    loadResources();
  }, []);

  return (
    <div className="content-card">
      <p className="section-label">Support</p>
      <h2>Helpful Resources</h2>

      {loading && <p>Loading resources...</p>}

      {error && !loading && <p>{error}</p>}

      {!loading && !error && resourceData && (
        <>
          <p>
            {resourceData.message ||
              "Additional support resources are available below."}
          </p>

          <ul className="resource-list">
            {resourceData.resourceLinks &&
              resourceData.resourceLinks.map((link, index) => (
                <li key={index}>
                  <a href={link} target="_blank" rel="noreferrer">
                    {link}
                  </a>
                </li>
              ))}
          </ul>
        </>
      )}

      {!loading && !error && !resourceData && (
        <p>No support resources available right now.</p>
      )}
    </div>
  );
}