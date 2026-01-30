import { useState } from 'react';
import { Check, Trash2, Edit2, X, Save, Calendar } from 'lucide-react';
import { cn } from '@/lib/utils';
import { Todo } from '@/types/todo';

interface TodoItemProps {
    todo: Todo;
    onToggle: (id: string) => void;
    onDelete: (id: string) => void;
    onEdit: (id: string, newTitle: string, newDescription?: string) => void;
}

export function TodoItem({ todo, onToggle, onDelete, onEdit }: TodoItemProps) {
    const [isEditing, setIsEditing] = useState(false);
    const [editTitle, setEditTitle] = useState(todo.title);
    const [editDescription, setEditDescription] = useState(todo.description || '');

    const handleSave = () => {
        if (!editTitle.trim()) return;
        onEdit(todo.id, editTitle, editDescription);
        setIsEditing(false);
    };

    const handleCancel = () => {
        setEditTitle(todo.title);
        setEditDescription(todo.description || '');
        setIsEditing(false);
    };

    return (
        <div className={cn(
            "group relative flex flex-col gap-3 p-5 rounded-2xl border transition-all duration-300 animate-in fade-in slide-in-from-bottom-2",
            todo.completed
                ? "bg-zinc-50 dark:bg-zinc-900/50 border-zinc-100 dark:border-zinc-800/50"
                : "bg-white dark:bg-zinc-900 border-zinc-200 dark:border-zinc-800 shadow-sm hover:shadow-md hover:border-orange-200 dark:hover:border-orange-900/50"
        )}>
            <div className="flex items-start gap-4">
                <button
                    onClick={() => onToggle(todo.id)}
                    className={cn(
                        "mt-1 flex-shrink-0 w-6 h-6 rounded-full border-2 flex items-center justify-center transition-all duration-300",
                        todo.completed
                            ? "bg-orange-500 border-orange-500 text-white"
                            : "border-zinc-300 dark:border-zinc-600 hover:border-orange-500 dark:hover:border-orange-500"
                    )}
                >
                    {todo.completed && <Check className="w-3.5 h-3.5" />}
                </button>

                <div className="flex-1 min-w-0">
                    {isEditing ? (
                        <div className="flex flex-col gap-3">
                            <input
                                type="text"
                                value={editTitle}
                                onChange={(e) => setEditTitle(e.target.value)}
                                className="w-full text-lg font-medium bg-zinc-50 dark:bg-zinc-800/50 rounded-lg px-3 py-2 border border-zinc-200 dark:border-zinc-700 focus:outline-none focus:ring-2 focus:ring-orange-500/20"
                                autoFocus
                            />
                            <textarea
                                value={editDescription}
                                onChange={(e) => setEditDescription(e.target.value)}
                                rows={2}
                                className="w-full text-sm bg-zinc-50 dark:bg-zinc-800/50 rounded-lg px-3 py-2 border border-zinc-200 dark:border-zinc-700 resize-none focus:outline-none focus:ring-2 focus:ring-orange-500/20"
                                placeholder="Description"
                            />
                        </div>
                    ) : (
                        <div className="flex flex-col gap-1">
                            <h3 className={cn(
                                "text-lg font-medium transition-all duration-300 break-words",
                                todo.completed && "text-zinc-400 dark:text-zinc-600 line-through decoration-2 decoration-zinc-300 dark:decoration-zinc-700"
                            )}>
                                {todo.title}
                            </h3>
                            {todo.description && (
                                <p className={cn(
                                    "text-sm text-zinc-600 dark:text-zinc-400 break-words",
                                    todo.completed && "opacity-60"
                                )}>
                                    {todo.description}
                                </p>
                            )}
                        </div>
                    )}

                    <div className="mt-3 flex items-center gap-3">
                        <span className="flex items-center gap-1 text-xs text-zinc-400 dark:text-zinc-600">
                            <Calendar className="w-3 h-3" />
                            {new Date(todo.createdAt).toLocaleDateString()}
                        </span>
                    </div>
                </div>

                <div className={cn(
                    "flex items-center gap-1 opacity-100 sm:opacity-0 group-hover:opacity-100 transition-opacity duration-200",
                    isEditing && "opacity-100"
                )}>
                    {isEditing ? (
                        <>
                            <button
                                onClick={handleSave}
                                className="p-2 text-green-600 hover:bg-green-50 dark:hover:bg-green-900/20 rounded-lg transition-colors"
                                title="Save"
                            >
                                <Save className="w-4 h-4" />
                            </button>
                            <button
                                onClick={handleCancel}
                                className="p-2 text-zinc-500 hover:bg-zinc-100 dark:hover:bg-zinc-800 rounded-lg transition-colors"
                                title="Cancel"
                            >
                                <X className="w-4 h-4" />
                            </button>
                        </>
                    ) : (
                        <>
                            <button
                                onClick={() => setIsEditing(true)}
                                className="p-2 text-zinc-500 hover:text-orange-600 hover:bg-orange-50 dark:hover:bg-orange-900/20 rounded-lg transition-colors"
                                title="Edit"
                            >
                                <Edit2 className="w-4 h-4" />
                            </button>
                            <button
                                onClick={() => onDelete(todo.id)}
                                className="p-2 text-zinc-500 hover:text-red-600 hover:bg-red-50 dark:hover:bg-red-900/20 rounded-lg transition-colors"
                                title="Delete"
                            >
                                <Trash2 className="w-4 h-4" />
                            </button>
                        </>
                    )}
                </div>
            </div>
        </div>
    );
}
