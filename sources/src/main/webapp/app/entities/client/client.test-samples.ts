import { IClient, NewClient } from './client.model';

export const sampleWithRequiredData: IClient = {
  id: 71655,
  refClient: 'hacking',
  nom: 'primary Virginia Concrete',
  prenom: 'asynchronous Account Future',
};

export const sampleWithPartialData: IClient = {
  id: 23980,
  refClient: 'Devolved Washington',
  nom: 'card Reverse-engineered analyzing',
  prenom: 'Som',
};

export const sampleWithFullData: IClient = {
  id: 40065,
  refClient: 'Facilitator',
  nom: 'seamless initiatives',
  prenom: 'auxiliary Drive yellow',
  contact: 'copy contextually-based azure',
};

export const sampleWithNewData: NewClient = {
  refClient: 'Portugal JSON parse',
  nom: 'connect',
  prenom: 'EXE Illinois Vatu',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
