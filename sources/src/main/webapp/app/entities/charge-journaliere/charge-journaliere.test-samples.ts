import dayjs from 'dayjs/esm';

import { TypeCharge } from 'app/entities/enumerations/type-charge.model';

import { IChargeJournaliere, NewChargeJournaliere } from './charge-journaliere.model';

export const sampleWithRequiredData: IChargeJournaliere = {
  id: 'dc2ffd79-9705-4275-9b3b-1852069a4ee9',
};

export const sampleWithPartialData: IChargeJournaliere = {
  id: '912bdd28-8caf-4319-b7be-c1358741949d',
  date: dayjs('2023-01-25'),
};

export const sampleWithFullData: IChargeJournaliere = {
  id: '173a67e3-b2b3-46e6-af8b-67d38dd82ac5',
  date: dayjs('2023-01-25'),
  type: TypeCharge['Support'],
  duree: 76993,
  description: 'primary AGP',
};

export const sampleWithNewData: NewChargeJournaliere = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
