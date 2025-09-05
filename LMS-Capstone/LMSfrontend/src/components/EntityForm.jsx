import React, { useState, useEffect } from 'react';

const EntityForm = ({ fields, initialData = {}, onSubmit, onCancel }) => {
  const [formData, setFormData] = useState({});

  useEffect(() => {
    setFormData(initialData);
  }, [initialData]);

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    onSubmit(formData);
  };

  return (
    <form onSubmit={handleSubmit} className="bg-white shadow-md rounded-md p-4 space-y-3">
      {fields.map((field) => (
        <div key={field.name}>
          <label className="block mb-1">{field.label}</label>
          {field.type === 'select' ? (
            <select name={field.name} value={formData[field.name] || ''} onChange={handleChange}>
              {field.options.map((opt) => (
                <option key={opt} value={opt}>{opt}</option>
              ))}
            </select>
          ) : (
            <input
              type={field.type || 'text'}
              name={field.name}
              value={formData[field.name] || ''}
              onChange={handleChange}
            />
          )}
        </div>
      ))}
      <div className="space-x-2">
        <button type="submit">Save</button>
        <button type="button" onClick={onCancel} className="bg-gray-500">Cancel</button>
      </div>
    </form>
  );
};

export default EntityForm;