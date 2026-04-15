import { useState } from "react";

export default function useMoodData() {
  const [data, setData] = useState([]);

  const addEntry = (entry) => {
    setData((prev) => [entry, ...prev]);
  };

  return { data, addEntry };
}