import { Link } from "react-router-dom";

export default function Dashboard() {
  const services = [
    { name: "ಸ್ವೀಕೃತಿಗಳು", path: "/acquisitions", caption: "ಹೊಸ ಪುಸ್ತಕಗಳನ್ನು ಸೇರಿಸಿ" },
    { name: "ಕೋಶ", path: "/catalogue", caption: "ಎಲ್ಲಾ ಪುಸ್ತಕಗಳನ್ನು ನಿರ್ವಹಿಸಿ" },
    { name: "ಪ್ರಸರಣ", path: "/circulation", caption: "ಪುಸ್ತಕಗಳನ್ನು ನೀಡುವುದು ಮತ್ತು ಹಿಂತಿರುಗಿಸುವುದು" },
    { name: "ಗ್ರಾಹಕರು", path: "/members", caption: "ಗ್ರಾಹಕರನ್ನು ನಿರ್ವಹಿಸಿ" },
    { name: "ವಿಮರ್ಶೆಗಳು", path: "/reviews", caption: "ಪುಸ್ತಕ ವಿಮರ್ಶೆಗಳನ್ನು ಪರಿಶೀಲಿಸಿ" },
  ];

  return (
    <div
      className="min-h-screen flex flex-col items-center justify-start bg-cover bg-center pt-20"
      style={{
        backgroundImage: `url('https://images.unsplash.com/photo-1507842217343-583bb7270b66?auto=format&fit=crop&w=1950&q=80')`,
        backgroundColor: '#1e1a18',
        backgroundBlendMode: 'multiply',
      }}
    >
      <h2 className="text-5xl font-extrabold text-yellow-400 mb-16 drop-shadow-2xl text-center">
  ಜ್ಞಾನ ಭಂಡಾರಕ್ಕೆ ಸ್ವಾಗತ! 📖
</h2>


      <div className="w-full max-w-6xl px-6 flex flex-col gap-6">
        {/* First row: 3 cards */}
        <div className="flex justify-between gap-6">
          {services.slice(0, 3).map((s) => (
            <Link
              key={s.name}
              to={s.path}
              className="flex-1 min-w-[220px] bg-[#2c241f]/80 shadow-2xl rounded-2xl p-10 flex flex-col items-center justify-center text-center text-yellow-300 border border-yellow-700 hover:bg-[#3e2c1c]/80 hover:shadow-3xl transition duration-300"
            >
              <span className="text-xl font-serif font-semibold mb-1">{s.name}</span>
              <span className="text-sm font-serif font-semibold text-yellow-200 leading-snug">{s.caption}</span>
            </Link>
          ))}
        </div>

        {/* Second row: 2 cards */}
        <div className="flex justify-center gap-6">
          {services.slice(3).map((s) => (
            <Link
              key={s.name}
              to={s.path}
              className="flex-1 min-w-[220px] bg-[#2c241f]/80 shadow-2xl rounded-2xl p-10 flex flex-col items-center justify-center text-center text-yellow-300 border border-yellow-700 hover:bg-[#3e2c1c]/80 hover:shadow-3xl transition duration-300"
            >
              <span className="text-xl font-serif font-semibold mb-1">{s.name}</span>
              <span className="text-sm font-serif font-semibold text-yellow-200 leading-snug">{s.caption}</span>
            </Link>
          ))}
        </div>
      </div>
    </div>
  );
}
