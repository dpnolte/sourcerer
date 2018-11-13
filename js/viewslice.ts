import { ElementNode, element } from './element';
import { LayoutParamAttributes } from './layoutparams';
import { MainTypes } from './main';
// types
/* generated @ 2018-11-13T17:11:45.832 */
export namespace ViewSliceTypes {
  export interface SliceViewAttributes extends MainTypes.ViewGroupAttributes {
  }
}
// elements
export const SliceView = (
  attributes?: ViewSliceTypes.SliceViewAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<ViewSliceTypes.SliceViewAttributes, LayoutParamAttributes> => {
  return element('sliceView', attributes, children);
};
