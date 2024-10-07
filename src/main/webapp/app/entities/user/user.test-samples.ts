import { IUser } from './user.model';

export const sampleWithRequiredData: IUser = {
  id: 6062,
  login: 'C56b83@l9\\ORzGws\\rM\\^CSr7\\~xJ9VdT',
};

export const sampleWithPartialData: IUser = {
  id: 19721,
  login: '5Z@4cFch\\twh60\\wN4GE\\me\\>nRXN\\W6',
};

export const sampleWithFullData: IUser = {
  id: 32691,
  login: 'rw',
};
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
