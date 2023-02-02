import dayjs from 'dayjs/esm';
import { IProjet } from 'app/entities/projet/projet.model';
import { Etat } from 'app/entities/enumerations/etat.model';

export interface ILivrable {
  id: number;
  refLivrable?: string | null;
  dateDebut?: dayjs.Dayjs | null;
  dateFin?: dayjs.Dayjs | null;
  description?: string | null;
  etat?: Etat | null;
  projet?: Pick<IProjet, 'id' | 'refProjet'> | null;
}

export type NewLivrable = Omit<ILivrable, 'id'> & { id: null };
