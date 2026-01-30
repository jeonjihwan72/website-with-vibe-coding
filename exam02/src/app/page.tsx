'use client';

import { useEffect, useState } from 'react';
import { useLocalStorage } from '@/hooks/useLocalStorage';
import { Todo } from '@/types/todo';
import { TodoForm } from '@/components/todo/TodoForm';
import { TodoList } from '@/components/todo/TodoList';
import { CheckSquare } from 'lucide-react';

export default function Home() {
  const [todos, setTodos] = useLocalStorage<Todo[]>('todos-v1', []);
  const [mounted, setMounted] = useState(false);

  // Avoid hydration mismatch
  useEffect(() => {
    setMounted(true);
  }, []);

  const handleAddTodo = (title: string, description?: string) => {
    const newTodo: Todo = {
      id: crypto.randomUUID(),
      title,
      description,
      completed: false,
      createdAt: new Date(),
    };
    setTodos((prev) => [newTodo, ...prev]);
  };

  const handleToggleTodo = (id: string) => {
    setTodos((prev) =>
      prev.map((todo) =>
        todo.id === id ? { ...todo, completed: !todo.completed } : todo
      )
    );
  };

  const handleDeleteTodo = (id: string) => {
    setTodos((prev) => prev.filter((todo) => todo.id !== id));
  };

  const handleEditTodo = (id: string, newTitle: string, newDescription?: string) => {
    setTodos((prev) =>
      prev.map((todo) =>
        todo.id === id
          ? { ...todo, title: newTitle, description: newDescription }
          : todo
      )
    );
  };

  if (!mounted) {
    return null; // Or a loading skeleton
  }

  return (
    <main className="min-h-screen bg-[#fafafa] dark:bg-black text-zinc-900 dark:text-zinc-100 transition-colors duration-300">
      <div className="max-w-3xl mx-auto px-4 py-12 sm:py-20">
        <header className="mb-10 space-y-2">
          <div className="flex items-center gap-3 mb-2">
            <div className="p-2.5 bg-orange-600 rounded-xl shadow-lg shadow-orange-600/20">
              <CheckSquare className="w-8 h-8 text-white" />
            </div>
            <h1 className="text-3xl sm:text-4xl font-bold tracking-tight text-zinc-900 dark:text-white">
              Vibe Todo
            </h1>
          </div>
          <p className="text-zinc-500 dark:text-zinc-400 text-lg max-w-md">
            Stay organized and get things done with focus and clarity.
          </p>
        </header>

        <div className="space-y-8">
          <section>
            <TodoForm onAdd={handleAddTodo} />
          </section>

          <section>
            <TodoList
              todos={todos}
              onToggle={handleToggleTodo}
              onDelete={handleDeleteTodo}
              onEdit={handleEditTodo}
            />
          </section>
        </div>

        <footer className="mt-20 pt-8 border-t border-zinc-200 dark:border-zinc-800 text-center text-sm text-zinc-400">
          <p>© {new Date().getFullYear()} Vibe Todo App. Created with Next.js 14.</p>
        </footer>
      </div>
    </main>
  );
}
