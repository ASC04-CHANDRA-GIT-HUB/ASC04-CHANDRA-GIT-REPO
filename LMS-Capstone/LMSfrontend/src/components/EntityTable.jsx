import React from 'react';

const EntityTable = ({ columns, data, onEdit, onDelete }) => {
  return (
    <table className="w-full border-collapse bg-white shadow-md rounded-md overflow-hidden">
      <thead className="bg-gray-200">
        <tr>
          {columns.map((col) => (
            <th key={col} className="px-4 py-2 text-left">{col}</th>
          ))}
          <th className="px-4 py-2">Actions</th>
        </tr>
      </thead>
      <tbody>
        {data.map((row, idx) => (
          <tr key={idx} className="border-b">
            {columns.map((col) => (
              <td key={col} className="px-4 py-2">{row[col]}</td>
            ))}
            <td className="px-4 py-2 space-x-2">
              <button onClick={() => onEdit(row)}>Edit</button>
              <button onClick={() => onDelete(row)}>Delete</button>
            </td>
          </tr>
        ))}
      </tbody>
    </table>
  );
};

export default EntityTable;