import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import { Observable } from 'rxjs';

import { TokenStorageService } from './token-storage.service';

@Injectable()
export class TokenService implements HttpInterceptor {
  constructor(private tokenStorageService:TokenStorageService) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    const authToken= this.tokenStorageService.getToken()

    const authRequest = !authToken ? req : req = req.clone({

      setHeaders: {

        'Authorization': `Bearer ${authToken}`,

      },

    });

    return next.handle(authRequest);

  }

  
}