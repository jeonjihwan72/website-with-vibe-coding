import { useState } from 'react';
import { Plus } from 'lucide-react';
import { cn } from '@/lib/utils';

interface TodoFormProps {
    onAdd: (title: string, description?: string) => void;
}

export function TodoForm({ onAdd }: TodoFormProps) {
    const [title, setTitle] = useState('');
    const [description, setDescription] = useState('');
    const [isExpanded, setIsExpanded] = useState(false);

    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault();
        if (!title.trim()) return;

        onAdd(title, description);
        setTitle('');
        setDescription('');
        setIsExpanded(false);
    };

    return (
        <form
            onSubmit={handleSubmit}
            className={cn(
                "w-full bg-white dark:bg-zinc-900 rounded-2xl shadow-sm border border-zinc-200 dark:border-zinc-800 overflow-hidden transition-all duration-300",
                isExpanded ? "p-6" : "p-2"
            )}
        >
            <div className={cn("flex flex-col gap-4", !isExpanded && "flex-row items-center")}>
                <div className="flex-1">
                    <input
                        type="text"
                        placeholder="Add a new task..."
                        value={title}
                        onChange={(e) => setTitle(e.target.value)}
                        onFocus={() => setIsExpanded(true)}
                        className="w-full text-lg font-medium bg-transparent border-none outline-none placeholder:text-zinc-400 dark:text-zinc-100 dark:placeholder:text-zinc-600"
                    />
                </div>

                {isExpanded && (
                    <textarea
                        placeholder="Description (optional)"
                        value={description}
                        onChange={(e) => setDescription(e.target.value)}
                        rows={3}
                        className="w-full resize-none text-sm bg-transparent border-none outline-none placeholder:text-zinc-400 dark:text-zinc-300 dark:placeholder:text-zinc-600"
                    />
                )}

                <div className={cn("flex justify-end gap-2", !isExpanded && "w-auto")}>
                    {isExpanded && (
                        <button
                            type="button"
                            onClick={() => setIsExpanded(false)}
                            className="px-4 py-2 text-sm font-medium text-zinc-600 dark:text-zinc-400 hover:text-zinc-900 dark:hover:text-zinc-200 transition-colors"
                        >
                            Cancel
                        </button>
                    )}
                    <button
                        type="submit"
                        disabled={!title.trim()}
                        className="flex items-center justify-center p-2 sm:px-4 sm:py-2 rounded-xl bg-orange-600 hover:bg-orange-700 text-white disabled:opacity-50 disabled:cursor-not-allowed transition-colors"
                    >
                        {isExpanded ? (
                            <>Add Task</>
                        ) : (
                            <Plus className="w-6 h-6 sm:hidden" />
                        )}
                        <span className={cn("hidden sm:inline", !isExpanded && "sr-only sm:not-sr-only sm:ml-0")}>
                            {!isExpanded && <Plus className="w-5 h-5" />}
                        </span>
                    </button>
                </div>
            </div>
        </form>
    );
}
