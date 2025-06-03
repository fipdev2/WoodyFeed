"use client";
import { useState } from "react";

type Hero =
  | "Homem de Ferro"
  | "Mulher-Maravilha"
  | "Homem-Aranha"
  | "Superman"
  | "Batman"
  | "Capitã Marvel"
  | "Flash"
  | "Thor";

type Question = {
  text: string;
  options: {
    text: string;
    hero: Hero;
  }[];
};

type HeroProfile = {
  description: string;
  image: string;
};

const heroProfiles: Record<Hero, HeroProfile> = {
  "Homem de Ferro": {
    description: "Você é inteligente, estratégico e sempre pensa à frente.",
    image: "https://upload.wikimedia.org/wikipedia/en/e/e0/Iron_Man_bleeding_edge.jpg",
  },
  "Mulher-Maravilha": {
    description: "Você é corajoso, justo e luta com bravura pelo que acredita.",
    image: "https://upload.wikimedia.org/wikipedia/en/9/93/Wonder_Woman.jpg",
  },
  "Homem-Aranha": {
    description: "Você é leal, engraçado e tem um grande senso de responsabilidade.",
    image: "https://upload.wikimedia.org/wikipedia/en/0/0c/Spiderman50.jpg",
  },
  "Superman": {
    description: "Você é nobre, poderoso e sempre faz o que é certo.",
    image: "https://upload.wikimedia.org/wikipedia/en/3/35/Supermanflying.png",
  },
  "Batman": {
    description: "Você é sombrio, determinado e usa a mente como sua maior arma.",
    image: "https://upload.wikimedia.org/wikipedia/en/8/8a/Batman_Cover_-_The_Batman_2006.jpg",
  },
  "Capitã Marvel": {
    description: "Você é forte, confiante e determinado a proteger os outros.",
    image: "https://upload.wikimedia.org/wikipedia/en/8/85/Captain_Marvel_2019_poster.jpg",
  },
  "Flash": {
    description: "Você é rápido, energético e sempre pronto para ajudar.",
    image: "https://upload.wikimedia.org/wikipedia/en/e/e1/Flash_Family.jpg",
  },
  "Thor": {
    description: "Você é poderoso, leal e valoriza sua herança.",
    image: "https://upload.wikimedia.org/wikipedia/en/4/4c/Thor_%28Marvel_Comics_character%29.png",
  },
};

const questions: Question[] = [
  {
    text: "Qual superpoder você gostaria de ter?",
    options: [
      { text: "Voar", hero: "Superman" },
      { text: "Super força", hero: "Mulher-Maravilha" },
      { text: "Inteligência avançada", hero: "Homem de Ferro" },
      { text: "Sentido Aranha", hero: "Homem-Aranha" },
    ],
  },
  {
    text: "Qual desses heróis você mais admira?",
    options: [
      { text: "Batman", hero: "Batman" },
      { text: "Capitã Marvel", hero: "Capitã Marvel" },
      { text: "Thor", hero: "Thor" },
      { text: "Flash", hero: "Flash" },
    ],
  },
  {
    text: "Qual dessas qualidades te define melhor?",
    options: [
      { text: "Inteligência", hero: "Homem de Ferro" },
      { text: "Coragem", hero: "Mulher-Maravilha" },
      { text: "Responsabilidade", hero: "Homem-Aranha" },
      { text: "Justiça", hero: "Superman" },
    ],
  },
  {
    text: "Como você age em uma emergência?",
    options: [
      { text: "Pensa rápido e age", hero: "Flash" },
      { text: "Age com força e bravura", hero: "Thor" },
      { text: "Cria um plano antes", hero: "Batman" },
      { text: "Confia em seu instinto", hero: "Capitã Marvel" },
    ],
  },
  {
    text: "O que você mais valoriza?",
    options: [
      { text: "Família e responsabilidade", hero: "Homem-Aranha" },
      { text: "Tecnologia e conhecimento", hero: "Homem de Ferro" },
      { text: "Força e tradição", hero: "Thor" },
      { text: "Velocidade e eficiência", hero: "Flash" },
    ],
  },
  {
    text: "Qual seu estilo de liderança?",
    options: [
      { text: "Inspirador e justo", hero: "Mulher-Maravilha" },
      { text: "Planejador e estratégico", hero: "Batman" },
      { text: "Autoconfiante e forte", hero: "Capitã Marvel" },
      { text: "Rápido e decisivo", hero: "Flash" },
    ],
  },
  {
    text: "Qual lugar você escolheria para descansar?",
    options: [
      { text: "Uma torre de alta tecnologia", hero: "Homem de Ferro" },
      { text: "Uma ilha paradisíaca", hero: "Mulher-Maravilha" },
      { text: "Um laboratório secreto", hero: "Batman" },
      { text: "Em meio à natureza", hero: "Thor" },
    ],
  },
  {
    text: "O que te motiva a seguir em frente?",
    options: [
      { text: "Proteger os inocentes", hero: "Superman" },
      { text: "Provar seu valor", hero: "Homem-Aranha" },
      { text: "Ser o mais rápido e eficiente", hero: "Flash" },
      { text: "Defender sua herança", hero: "Thor" },
    ],
  },
];


export default function Quiz() {
  const [username, setUsername] = useState("");
  const [loggedIn, setLoggedIn] = useState(false);
  const [currentQuestionIndex, setCurrentQuestionIndex] = useState(0);
  const [answers, setAnswers] = useState<Hero[]>([]);
  const [showResult, setShowResult] = useState(false);
  const [resultHero, setResultHero] = useState<Hero | null>(null);

  function startQuiz(e: React.FormEvent) {
    e.preventDefault();
    if (username.trim() === "@usuario_01") {
      setLoggedIn(true);
    } else {
      alert("Para entrar, digite exatamente: @usuario_01");
    }
  }

  function selectAnswer(hero: Hero) {
    const newAnswers = [...answers, hero];
    setAnswers(newAnswers);

    if (currentQuestionIndex + 1 < questions.length) {
      setCurrentQuestionIndex(currentQuestionIndex + 1);
    } else {
      const counts: Record<Hero, number> = Object.fromEntries(
        Object.keys(heroProfiles).map((h) => [h, 0])
      ) as Record<Hero, number>;

      newAnswers.forEach((h) => counts[h]++);
      const maxScore = Math.max(...Object.values(counts));
      const tiedHeroes = Object.entries(counts)
        .filter(([_, count]) => count === maxScore)
        .map(([hero]) => hero as Hero);

      const chosenHero = tiedHeroes[Math.floor(Math.random() * tiedHeroes.length)];
      setResultHero(chosenHero);

      setShowResult(true);
    }
  }

  function restart() {
    setLoggedIn(false);
    setUsername("");
    setCurrentQuestionIndex(0);
    setAnswers([]);
    setShowResult(false);
    setResultHero(null);
  }

  return (
    <div className="min-h-screen bg-gray-900 text-white flex flex-col items-center justify-center p-6">
      <header className="mb-10 text-center">
        <h1 className="text-4xl font-extrabold mb-2">QUIZ - Qual super-herói é você?</h1>
        {!loggedIn && (
          <p className="text-gray-400">Digite seu @ para começar</p>
        )}
      </header>

      {!loggedIn && (
        <form
          onSubmit={startQuiz}
          className="w-full max-w-md flex flex-col gap-4"
        >
          <input
            type="text"
            placeholder="@usuario_01"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            className="border p-3 rounded-md text-gray-400 focus:outline-none border-gray-400 focus:ring-1 focus:ring-red-600"
            required
          />
          <button
            type="submit"
            className="bg-red-600 hover:bg-red-700 font-semibold rounded-md py-3 transition"
          >
            Começar Quiz
          </button>
        </form>
      )}

      {loggedIn && !showResult && (
        <section className="w-full max-w-md bg-gray-800 rounded-lg p-6 shadow-lg">
          <h2 className="text-2xl font-semibold mb-4">
            {questions[currentQuestionIndex].text}
          </h2>
          <ul className="flex flex-col gap-3">
            {questions[currentQuestionIndex].options.map((option, idx) => (
              <li key={idx}>
                <button
                  onClick={() => selectAnswer(option.hero)}
                  className="w-full text-left bg-gray-700 hover:bg-red-600 rounded-md px-4 py-3 transition"
                >
                  {option.text}
                </button>
              </li>
            ))}
          </ul>
        </section>
      )}

      {showResult && resultHero && (
        <section className="w-full max-w-md bg-gray-800 rounded-lg p-6 shadow-lg text-center">
          <img
            src={heroProfiles[resultHero].image}
            alt={resultHero}
            className="mx-auto mb-4 rounded-full w-36 h-36 object-cover"
          />
          <h2 className="text-3xl font-bold mb-2">{resultHero}</h2>
          <p className="mb-6">{heroProfiles[resultHero].description}</p>
          <button
            onClick={restart}
            className="bg-red-600 hover:bg-red-700 font-semibold rounded-md py-3 px-6 transition"
          >
            Refazer Quiz
          </button>
        </section>
      )}
    </div>
  );
}
