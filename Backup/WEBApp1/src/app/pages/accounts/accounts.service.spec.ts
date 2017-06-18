import { TestBed, inject } from '@angular/core/testing';

import { AccountsService } from '../../services/accounts.service';

describe('AccountsService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AccountsService]
    });
  });

  it('should ...', inject([AccountsService], (service: AccountsService) => {
    expect(service).toBeTruthy();
  }));
});
