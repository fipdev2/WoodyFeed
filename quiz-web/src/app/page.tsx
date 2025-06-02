"use client";
import { useState } from "react";

type Question = {
  text: string;
  options: string[];
  answer: number | null;
};

type UserProfile = {
  questions: Question[];
  result: {
    name: string;
    description: string;
    image: string;
  };
};

const users: Record<string, UserProfile> = {
  "@usuario_01": {
    questions: [
      {
        text: "Qual seu jogo preferido?",
        options: ["CS:GO", "League of Legends", "Valorant", "Futebol"],
        answer: null,
      },
      {
        text: "Qual seu papel favorito no jogo?",
        options: ["Atacante", "Defensor", "Suporte", "Líder"],
        answer: null,
      },
    ],
    result: {
      name: "Usuário 01",
      description:
        "Você é um fã apaixonado de CS:GO e gosta de ser agressivo no jogo!",
      image: "https://furia-match.vercel.app/assets/user01.png",
    },
  },
  "@usuario_02": {
    questions: [
      {
        text: "Qual seu jogo preferido?",
        options: ["CS:GO", "League of Legends", "Valorant", "Futebol"],
        answer: null,
      },
    ],
    result: {
      name: "Usuário 02",
      description: "Você curte League of Legends e é um ótimo estrategista!",
      image: "https://furia-match.vercel.app/assets/user02.png",
    },
  },
};

export default function Quiz() {
  const [username, setUsername] = useState("");
  const [currentUser, setCurrentUser] = useState<UserProfile | null>(null);
  const [currentQuestionIndex, setCurrentQuestionIndex] = useState(0);
  const [showResult, setShowResult] = useState(false);

  function startQuiz(e: React.FormEvent) {
    e.preventDefault();
    const user = users[username.trim()];
    if (user) {
      // reset answers on start
      user.questions.forEach((q) => (q.answer = null));
      setCurrentUser(user);
      setCurrentQuestionIndex(0);
      setShowResult(false);
    } else {
      alert("Usuário não encontrado. Tente @usuario_01 ou @usuario_02");
    }
  }

  function selectAnswer(index: number) {
    if (!currentUser) return;
    currentUser.questions[currentQuestionIndex].answer = index;
    if (currentQuestionIndex + 1 < currentUser.questions.length) {
      setCurrentQuestionIndex(currentQuestionIndex + 1);
    } else {
      setShowResult(true);
    }
  }

  function restart() {
    setUsername("");
    setCurrentUser(null);
    setCurrentQuestionIndex(0);
    setShowResult(false);
  }

  return (
    <div className="min-h-screen bg-gray-900 text-white flex flex-col items-center justify-center p-6">

      <div>
        <img src="" alt="" />
      </div>
      <header className="mb-10 text-center">
        <h1 className="text-4xl font-extrabold mb-2"> SUPER HEROS - Quiz</h1>
        <p className="text-gray-400">
          Digite seu handle para começar
        </p>
      </header>

      {!currentUser && (
        <form
          onSubmit={startQuiz}
          className="w-full max-w-md flex flex-col gap-4"
          aria-label="Formulário de login"
        >
          <input
            type="text"
            placeholder="@usuario_01"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            className="border p-3 rounded-md text-gray-400 focus:outline-none border-gray-400 focus:ring-1 focus:ring-red-600"
            required
            aria-required="true"
          />
          <button
            type="submit"
            className="bg-red-600 hover:bg-red-700 font-semibold rounded-md py-3 transition"
          >
            Começar Quiz
          </button>
        </form>
      )}

      {currentUser && !showResult && (
        <section className="w-full max-w-md bg-gray-800 rounded-lg p-6 shadow-lg">
          <h2 className="text-2xl font-semibold mb-4">
            {currentUser.questions[currentQuestionIndex].text}
          </h2>
          <ul className="flex flex-col gap-3">
            {currentUser.questions[currentQuestionIndex].options.map(
              (option, idx) => (
                <li key={idx}>
                  <button
                    onClick={() => selectAnswer(idx)}
                    className="w-full text-left bg-gray-700 hover:bg-red-600 rounded-md px-4 py-3 transition"
                    aria-label={`Selecionar opção: ${option}`}
                  >
                    {option}
                  </button>
                </li>
              )
            )}
          </ul>
        </section>
      )}

      {showResult && currentUser && (
        <section className="w-full max-w-md bg-gray-800 rounded-lg p-6 shadow-lg text-center">
          <img
            src={currentUser.result.image}
            alt={currentUser.result.name}
            className="mx-auto mb-4 rounded-full w-36 h-36 object-cover"
          />
          <h2 className="text-3xl font-bold mb-2">{currentUser.result.name}</h2>
          <p className="mb-6">{currentUser.result.description}</p>
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
