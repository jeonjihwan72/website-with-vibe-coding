import { Todo } from '@/types/todo';
import { TodoItem } from './TodoItem';
import { cn } from '@/lib/utils';
import { Inbox } from 'lucide-react';

interface TodoListProps {
    todos: Todo[];
    onToggle: (id: string) => void;
    onDelete: (id: string) => void;
    onEdit: (id: string, newTitle: string, newDescription?: string) => void;
}

export function TodoList({ todos, onToggle, onDelete, onEdit }: TodoListProps) {
    if (todos.length === 0) {
        return (
            <div className="flex flex-col items-center justify-center py-16 px-4 text-center">
                <div className="bg-zinc-100 dark:bg-zinc-800/50 p-4 rounded-full mb-4">
                    <Inbox className="w-8 h-8 text-zinc-400 dark:text-zinc-500" />
                </div>
                <h3 className="text-xl font-semibold text-zinc-900 dark:text-zinc-100 mb-2">
                    No tasks yet
                </h3>
                <p className="text-zinc-500 dark:text-zinc-400 max-w-sm">
                    Add your first task above to get started with your productivity journey.
                </p>
            </div>
        );
    }

    const activeTodos = todos.filter(t => !t.completed);
    const completedTodos = todos.filter(t => t.completed);

    return (
        <div className="space-y-8 animate-in fade-in duration-500">
            <div className="space-y-3">
                {activeTodos.map((todo) => (
                    <TodoItem
                        key={todo.id}
                        todo={todo}
                        onToggle={onToggle}
                        onDelete={onDelete}
                        onEdit={onEdit}
                    />
                ))}
            </div>

            {completedTodos.length > 0 && (
                <div className="space-y-3">
                    <h2 className="text-sm font-semibold text-zinc-500 dark:text-zinc-400 uppercase tracking-wider px-1">
                        Completed ({completedTodos.length})
                    </h2>
                    <div className="opacity-75">
                        {completedTodos.map((todo) => (
                            <TodoItem
                                key={todo.id}
                                todo={todo}
                                onToggle={onToggle}
                                onDelete={onDelete}
                                onEdit={onEdit}
                            />
                        ))}
                    </div>
                </div>
            )}
        </div>
    );
}
