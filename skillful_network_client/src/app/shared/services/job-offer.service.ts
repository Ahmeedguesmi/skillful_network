import { Injectable } from '@angular/core';
import { JobOffer } from '../models/job-offer';
import { MOCK_JOBOFFER } from '../models/mock.job-offers';
import { ApiHelperService } from './api-helper.service';


@Injectable({
    providedIn: 'root'
})
export class JobOfferService {
    public jobOffers: JobOffer[];

    constructor(private api: ApiHelperService) {
        this.jobOffers = [];
        MOCK_JOBOFFER.forEach(job => {
            this.jobOffers.push(new JobOffer(job));
        });
    }

    public findById(id: number): JobOffer {
        return this.jobOffers[id];
    }

    // Import depuis le MOCK
    public findAllMock(): JobOffer[] {
        return this.jobOffers;
    }

    // Import depuis le Backend
    public findAll(page: number, size: number): Promise<any> {
        let promise = new Promise((resolve, reject) => {
            this.api.get({ endpoint: '/offers/', queryParams: {page: page, size: size } })
                .then(
                    res => {
                        resolve(res);
                    },
                    msg => {
                        reject(msg);
                    }
                ).catch((error) => {
                });
        });
        return promise;
    }

    public getOffersBySearch(keyword: string, page: number, size: number): Promise<any> {
        let promise = new Promise((resolve, reject) => {
            this.api.get({ endpoint: `/offers/search`, queryParams: { keyword, page: page, size: size } })
                .then(
                    res => {
                        resolve(res);
                    },
                    msg => {
                        reject(msg);
                    }
                ).catch((error) => {
                    console.log("request not found")
                });
            });
            return promise;
        }
}
