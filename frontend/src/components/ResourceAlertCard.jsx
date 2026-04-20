import { useEffect, useState } from "react";
import { getSupportResources } from "../api/api";

export default function ResourceAlertCard() {
  const [resourceData, setResourceData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    async function loadResources() {
      try {
        setLoading(true);
        setError("");
        const data = await getSupportResources();
        setResourceData(data);
      } catch (err) {
        console.error("Failed to load resources:", err);
        setError(err.message || "Failed to load support resources.");
      } finally {
        setLoading(false);
      }
    }

    loadResources();
  }, []);

  const links =
    resourceData?.resourceLinks ||
    resourceData?.links ||
    resourceData?.resources ||
    [];

  return (
    <div className="card">
      <p className="summary-label">Support</p>
      <h2>Helpful Resources</h2>

      {loading && <p>Loading resources...</p>}
      {error && !loading && <p className="error-message">{error}</p>}

      {!loading && !error && (
        <>
          <p>
            {resourceData?.message ||
              "Additional support resources are available below."}
          </p>

          {Array.isArray(links) && links.length > 0 ? (
            <ul className="history-list">
              {links.map((link, index) => (
                <li key={index}>
                  <a href={link} target="_blank" rel="noreferrer">
                    {link}
                  </a>
                </li>
              ))}
            </ul>
          ) : (
            <p>No support resources available right now.</p>
          )}
        </>
      )}
    </div>
  );
}