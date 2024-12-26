import dayjs from 'dayjs/esm';

import { IAttendance, NewAttendance } from './attendance.model';

export const sampleWithRequiredData: IAttendance = {
  id: 27265,
  dateOfwork: dayjs('2024-10-07'),
  checkInTime: dayjs('2024-10-07T22:53'),
  checkOutTime: dayjs('2024-10-07T19:05'),
  workHour: 1317.07,
};

export const sampleWithPartialData: IAttendance = {
  id: 31432,
  dateOfwork: dayjs('2024-10-07'),
  checkInTime: dayjs('2024-10-07T18:26'),
  checkOutTime: dayjs('2024-10-07T11:06'),
  workHour: 23848.3,
};

export const sampleWithFullData: IAttendance = {
  id: 21696,
  dateOfwork: dayjs('2024-10-07'),
  checkInTime: dayjs('2024-10-07T07:01'),
  checkOutTime: dayjs('2024-10-07T21:52'),
  workHour: 14728.4,
};

export const sampleWithNewData: NewAttendance = {
  dateOfwork: dayjs('2024-10-07'),
  checkInTime: dayjs('2024-10-07T15:42'),
  checkOutTime: dayjs('2024-10-07T03:37'),
  workHour: 29484.95,
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
