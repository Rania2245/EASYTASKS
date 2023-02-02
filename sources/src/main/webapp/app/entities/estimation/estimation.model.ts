import dayjs from 'dayjs/esm';
import { IActivite } from 'app/entities/activite/activite.model';
import { IProjet } from 'app/entities/projet/projet.model';
import { ILivrable } from 'app/entities/livrable/livrable.model';
import { TypeEntite } from 'app/entities/enumerations/type-entite.model';

export interface IEstimation {
  id: string;
  date?: dayjs.Dayjs | null;
  valeurJour?: number | null;
  valeurHeure?: number | null;
  priseEnCharge?: boolean | null;
  type?: TypeEntite | null;
  activite?: Pick<IActivite, 'id' | 'refAct'> | null;
  projet?: Pick<IProjet, 'id' | 'refProjet'> | null;
  livrable?: Pick<ILivrable, 'id' | 'refLivrable'> | null;
}

export type NewEstimation = Omit<IEstimation, 'id'> & { id: null };
