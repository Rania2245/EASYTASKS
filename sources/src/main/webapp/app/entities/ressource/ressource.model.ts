export interface IRessource {
  id: string;
  nom?: string | null;
  prenom?: string | null;
}

export type NewRessource = Omit<IRessource, 'id'> & { id: null };
