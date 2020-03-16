import { User } from './user';

export class Skill {

    private _id: number;
    private _name: string;
    private _userSet: User[];

    constructor(data: any) {
        this._id = data.id;
        this._name = data.name;
        this._userSet = data.userSet;
    }

    public get id(): number {
        return this._id;
    }
    public set id(value: number) {
        this._id = value;
    }
    public set name(value: string) {
        this._name = value;
    }
    public get name(): string {
        return this._name;
    }
    public set userSet(value: User[]) {
        this._userSet = value;
    }
    public get userSet(): User[] {
        return this._userSet;
    }

}
