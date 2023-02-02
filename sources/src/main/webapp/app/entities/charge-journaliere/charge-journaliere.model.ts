import dayjs from 'dayjs/esm';
import { IRessource } from 'app/entities/ressource/ressource.model';
import { TypeCharge } from 'app/entities/enumerations/type-charge.model';

export interface IChargeJournaliere {
  id: string;
  date?: dayjs.Dayjs | null;
  type?: TypeCharge | null;
  duree?: number | null;
  description?: string | null;
  ressource?: Pick<IRessource, 'id' | 'nom'> | null;
}

export type NewChargeJournaliere = Omit<IChargeJournaliere, 'id'> & { id: null };
