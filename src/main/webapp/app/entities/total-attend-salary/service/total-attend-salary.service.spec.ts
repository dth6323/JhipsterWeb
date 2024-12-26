import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { ITotalAttendSalary } from '../total-attend-salary.model';
import { sampleWithFullData, sampleWithNewData, sampleWithPartialData, sampleWithRequiredData } from '../total-attend-salary.test-samples';

import { TotalAttendSalaryService } from './total-attend-salary.service';

const requireRestSample: ITotalAttendSalary = {
  ...sampleWithRequiredData,
};

describe('TotalAttendSalary Service', () => {
  let service: TotalAttendSalaryService;
  let httpMock: HttpTestingController;
  let expectedResult: ITotalAttendSalary | ITotalAttendSalary[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(TotalAttendSalaryService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should create a TotalAttendSalary', () => {
      const totalAttendSalary = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(totalAttendSalary).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a TotalAttendSalary', () => {
      const totalAttendSalary = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(totalAttendSalary).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a TotalAttendSalary', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of TotalAttendSalary', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a TotalAttendSalary', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addTotalAttendSalaryToCollectionIfMissing', () => {
      it('should add a TotalAttendSalary to an empty array', () => {
        const totalAttendSalary: ITotalAttendSalary = sampleWithRequiredData;
        expectedResult = service.addTotalAttendSalaryToCollectionIfMissing([], totalAttendSalary);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(totalAttendSalary);
      });

      it('should not add a TotalAttendSalary to an array that contains it', () => {
        const totalAttendSalary: ITotalAttendSalary = sampleWithRequiredData;
        const totalAttendSalaryCollection: ITotalAttendSalary[] = [
          {
            ...totalAttendSalary,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addTotalAttendSalaryToCollectionIfMissing(totalAttendSalaryCollection, totalAttendSalary);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a TotalAttendSalary to an array that doesn't contain it", () => {
        const totalAttendSalary: ITotalAttendSalary = sampleWithRequiredData;
        const totalAttendSalaryCollection: ITotalAttendSalary[] = [sampleWithPartialData];
        expectedResult = service.addTotalAttendSalaryToCollectionIfMissing(totalAttendSalaryCollection, totalAttendSalary);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(totalAttendSalary);
      });

      it('should add only unique TotalAttendSalary to an array', () => {
        const totalAttendSalaryArray: ITotalAttendSalary[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const totalAttendSalaryCollection: ITotalAttendSalary[] = [sampleWithRequiredData];
        expectedResult = service.addTotalAttendSalaryToCollectionIfMissing(totalAttendSalaryCollection, ...totalAttendSalaryArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const totalAttendSalary: ITotalAttendSalary = sampleWithRequiredData;
        const totalAttendSalary2: ITotalAttendSalary = sampleWithPartialData;
        expectedResult = service.addTotalAttendSalaryToCollectionIfMissing([], totalAttendSalary, totalAttendSalary2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(totalAttendSalary);
        expect(expectedResult).toContain(totalAttendSalary2);
      });

      it('should accept null and undefined values', () => {
        const totalAttendSalary: ITotalAttendSalary = sampleWithRequiredData;
        expectedResult = service.addTotalAttendSalaryToCollectionIfMissing([], null, totalAttendSalary, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(totalAttendSalary);
      });

      it('should return initial array if no TotalAttendSalary is added', () => {
        const totalAttendSalaryCollection: ITotalAttendSalary[] = [sampleWithRequiredData];
        expectedResult = service.addTotalAttendSalaryToCollectionIfMissing(totalAttendSalaryCollection, undefined, null);
        expect(expectedResult).toEqual(totalAttendSalaryCollection);
      });
    });

    describe('compareTotalAttendSalary', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareTotalAttendSalary(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareTotalAttendSalary(entity1, entity2);
        const compareResult2 = service.compareTotalAttendSalary(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareTotalAttendSalary(entity1, entity2);
        const compareResult2 = service.compareTotalAttendSalary(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareTotalAttendSalary(entity1, entity2);
        const compareResult2 = service.compareTotalAttendSalary(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
